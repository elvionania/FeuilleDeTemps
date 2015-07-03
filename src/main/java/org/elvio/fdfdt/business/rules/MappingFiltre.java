package org.elvio.fdfdt.business.rules;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.elvio.fdfdt.business.process.FilterFactory;
import org.elvio.fdfdt.file.conf.FinalConfiguration;
import org.elvio.fdfdt.file.conf.HoraireConfiguration;
import org.elvio.fdfdt.file.conf.meta.ClassConfiguration;
import org.elvio.fdfdt.file.conf.meta.Information;
import org.elvio.fdfdt.file.conf.meta.cell.Cell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfTimeCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfNumberCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfStringCell;
import org.elvio.fdfdt.file.conf.meta.row.BodyRow;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;
import org.elvio.fdfdt.file.conf.meta.row.FooterRow;
import org.elvio.fdfdt.util.CellFormatUtilities;

public class MappingFiltre implements Filter {

	private Cell employeeStartTime;
	private Cell employeeEndTime;
	private Cell employeePauseTime;

	public Information applyFilter(Information configuration) {

		Information resultat = new Information();

		for (Map.Entry<String, ClassConfiguration> employee : configuration.getConfigurations().entrySet()) {
			employeeStartTime = configuration.getDependancyEntry(FilterFactory.PLANNING,
					HoraireConfiguration.class.getName(), HoraireConfiguration.NOM, employee.getKey(),
					HoraireConfiguration.HEURE_DEBUT);
			employeeEndTime = configuration.getDependancyEntry(FilterFactory.PLANNING,
					HoraireConfiguration.class.getName(), HoraireConfiguration.NOM, employee.getKey(),
					HoraireConfiguration.HEURE_FIN);
			employeePauseTime = configuration.getDependancyEntry(FilterFactory.PLANNING,
					HoraireConfiguration.class.getName(), HoraireConfiguration.NOM, employee.getKey(),
					HoraireConfiguration.PAUSE);

			resultat.add(employee.getKey(), mapping(employee));
			
		}

		return resultat;
	}

	private void doFooter(FinalConfiguration config) {
		StringBuilder formulaAmount = new StringBuilder();
		for(int i = 2 ; i <= config.getRows(BodyRow.class).size()+1 ; i++){
			formulaAmount.append("L"+i);
			formulaAmount.append("+");
		}
		
		String amount = "";
		
		if(formulaAmount.toString().endsWith("+")){
			amount = formulaAmount.toString().substring(0, formulaAmount.toString().lastIndexOf("+"));
		}else{
			amount = formulaAmount.toString();
		}
		
		config.getRows(FooterRow.class).get(0).setCellValue(11, amount);
	}

	private ClassConfiguration mapping(Entry<String, ClassConfiguration> employee) {

		FinalConfiguration config = new FinalConfiguration();
		BodyRow outputRow = config.getBodyRowModel();
		Map<Date, List<ConfRow>> rowByDate = new TreeMap<Date, List<ConfRow>>();
		Date dayForRow = null;

		for (ConfRow inputRow : employee.getValue().getRows()) {
			dayForRow = getDay(inputRow);
			List<ConfRow> rowsForDate = rowByDate.get(dayForRow);
			if (rowsForDate == null) {
				rowsForDate = new ArrayList<ConfRow>();
				rowByDate.put(dayForRow, rowsForDate);
			}
			rowsForDate.add(inputRow.getCurrentRow());
		}

		for (Entry<Date, List<ConfRow>> rowEntry : rowByDate.entrySet()) {
			((BodyRow) outputRow).addNewRow();

			outputRow.setCellValue(0, rowEntry.getKey());
			outputRow.setCellValue(1, getFirstSequence(rowEntry.getValue()));
			outputRow.setCellValue(2, getLastSequence(rowEntry.getValue()));
			outputRow.setCellValue(3,
					((ConfStringCell) rowEntry.getValue().get(0).getCurrentRow().getCell(10)).getValue());
			outputRow.setCellValue(4, ((ConfStringCell) rowEntry.getValue().get(0).getCell(7)).getValue());
			outputRow.setCellValue(5, getDatePrevue(rowEntry.getValue(), employeeStartTime));
			if (getFirstHour(rowEntry.getValue()) != null) {
				outputRow.setCellValue(6, getFirstHour(rowEntry.getValue()));
			}
			outputRow.setCellValue(7, getDatePrevue(rowEntry.getValue(), employeeEndTime));
			if (getLastHour(rowEntry.getValue()) != null) {
				outputRow.setCellValue(8, getLastHour(rowEntry.getValue()));
			}

			outputRow.setCellValue(9, getPause(employeePauseTime));
			outputRow.setCellValue(10, getPause(employeePauseTime));
			outputRow.setCellValue(11, calculHeures(outputRow));
			outputRow.setCellValue(12, 0d);
			outputRow.setCellValue(13, 0d);
			outputRow.setCellValue(14, 0d);
			outputRow.setCellValue(15, 0d);
			outputRow.setCellValue(16, 0d);
			outputRow.setCellValue(17, 0d);
			outputRow.setCellValue(18, 0d);
			outputRow.setCellValue(19, 0d);
			outputRow.setCellValue(20, getLate(outputRow));
		}
		
		doFooter(config);

		return config;
	}

	private Date getPause(Cell cellPause) {
		Date pause = getDateFromDouble(0d);
		ConfNumberCell cell;
		if (!(cellPause instanceof ConfNumberCell)) {
			return pause;
		} else {
			cell = (ConfNumberCell) cellPause;
		}

		if (cell != null) {
			if (cell.getValue() != null) {
				// conversion en ms, getDateFromDouble utilise les ms
				pause = getDateFromDouble(cell.getValue() * 3600 * 1000);
			}
		}
		return pause;
	}

	private Date getLate(ConfRow outputRow) {
		double realStartHour = getTimeMs((ConfTimeCell) outputRow.getCurrentRow().getCell(6));
		double theoricStartSecond = getTimeMs((ConfTimeCell) outputRow.getCurrentRow().getCell(5));

		// regle du quart d'heure avant
		theoricStartSecond -= (15 * 60 * 1000);

		if (realStartHour == -1 || theoricStartSecond == -1) {
			return null;
		}

		if (realStartHour > theoricStartSecond) {
			double resultat = (realStartHour - theoricStartSecond);
			return getDateFromDouble(resultat);
		} else {
			return null;
		}
	}

	private long getTimeMs(ConfTimeCell cell) {
		Calendar calendar = GregorianCalendar.getInstance();
		if (cell.getValue() == null) {
			return -1l;
		}
		calendar.setTime(cell.getValue());

		return calendar.getTimeInMillis();
	}

	private String calculHeures(ConfRow outputRow) {

		int rowNumber = outputRow.getCount();
		int endHourColumn = 8;
		int startHourColumn = 0;
		int pauseColumn = 0;

		long endHour = getTimeMs((ConfTimeCell) outputRow.getCurrentRow().getCell(8));
		if (endHour == -1) {
			endHour = getTimeMs((ConfTimeCell) outputRow.getCurrentRow().getCell(7));
			endHourColumn = 7;
		}

		startHourColumn = 5;

		pauseColumn = 10;

		String formula = CellFormatUtilities.convertColumnIndex(endHourColumn) + (rowNumber + 1) + " - "
				+ CellFormatUtilities.convertColumnIndex(startHourColumn) + (rowNumber + 1) + " - "
				+ CellFormatUtilities.convertColumnIndex(pauseColumn) + (rowNumber + 1);

		return formula;
	}

	private Date getDateFromDouble(double resultat) {
		double hour = resultat / 1000 / 3600;
		double minute = (resultat - ((int) hour) * 3600000) / 60000;
		double seconde = (resultat - ((int) hour) * 3600000 - ((int) minute) * 60000) / 1000;

		Calendar calendar = GregorianCalendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY, (int) hour);
		calendar.set(Calendar.MINUTE, (int) minute);
		calendar.set(Calendar.SECOND, (int) seconde);

		return calendar.getTime();
	}

	private Date getLastHour(List<ConfRow> value) {

		int hour = 0;
		ConfRow selectedRow = null;

		if (value.size() == 0) {
			return null;
		}

		if (value.size() == 1) {
			hour = getHour(((ConfTimeCell) value.get(0).getCell(1)));
			if (hour < 15) {
				return null;
			} else {
				return ((ConfTimeCell) value.get(0).getCell(1)).getValue();
			}
		}

		for (ConfRow row : value) {
			int presentHour = getHour(((ConfTimeCell) row.getCell(1)));
			if (presentHour != hour) {
				selectedRow = row;
			}
		}

		return ((ConfTimeCell) selectedRow.getCell(1)).getValue();
	}

	private Date getFirstHour(List<ConfRow> value) {
		int hour = 0;
		ConfRow selectedRow = null;

		if (value.size() == 0) {
			return null;
		}

		if (value.size() == 1) {
			hour = getHour(((ConfTimeCell) value.get(0).getCell(1)));
			if (hour > 15) {
				return null;
			} else {
				return ((ConfTimeCell) value.get(0).getCell(1)).getValue();
			}
		}

		for (ConfRow row : value) {
			int presentHour = getHour(((ConfTimeCell) row.getCell(1)));

			if (hour == 0) {
				hour = presentHour;
				selectedRow = row;
			} else if (presentHour == hour) {
				selectedRow = row;
			} else {
				break;
			}
		}
		if (hour > 15) {
			return null;
		}
		return ((ConfTimeCell) selectedRow.getCell(1)).getValue();
	}

	private Date getDatePrevue(List<ConfRow> list, Cell date2Process) {
		double date2ProcessValue;
		if (date2Process instanceof ConfNumberCell) {
			date2ProcessValue = ((ConfNumberCell) date2Process).getValue();
		} else {
			return null;
		}
		ConfRow row = list.get(0);
		Date date = ((ConfTimeCell) row.getCell(1)).getValue();

		Calendar calendar = GregorianCalendar.getInstance();

		int hour = (int) date2ProcessValue;
		int minute = (int) ((double) (date2ProcessValue - hour) * 60);

		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	private Double getLastSequence(List<ConfRow> value) {

		int hour = 0;
		ConfRow selectedRow = null;

		if (value.size() == 0) {
			return null;
		}

		if (value.size() == 1) {
			hour = getHour(((ConfTimeCell) value.get(0).getCell(1)));
			if (hour < 15) {
				return null;
			} else {
				return (Double) value.get(0).getCell(0).getValue();
			}
		}

		for (ConfRow row : value) {
			int presentHour = getHour(((ConfTimeCell) row.getCell(1)));
			if (presentHour != hour) {
				selectedRow = row;
			}
		}

		return (Double) selectedRow.getCell(0).getValue();
	}

	private Double getFirstSequence(List<ConfRow> value) {
		int hour = 0;
		ConfRow selectedRow = null;

		if (value.size() == 0) {
			return null;
		}

		if (value.size() == 1) {
			hour = getHour(((ConfTimeCell) value.get(0).getCell(1)));
			if (hour > 15) {
				return null;
			} else {
				return (Double) value.get(0).getCell(0).getValue();
			}
		}

		for (ConfRow row : value) {
			int presentHour = getHour(((ConfTimeCell) row.getCell(1)));

			if (hour == 0) {
				hour = presentHour;
				selectedRow = row;
			} else if (presentHour == hour) {
				selectedRow = row;
			} else {
				break;
			}
		}
		if (hour > 15) {
			return null;
		}
		return (Double) selectedRow.getCell(0).getValue();
	}

	private Date getDay(ConfRow inputRow) {
//		String resultat = "";
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(((ConfTimeCell) inputRow.getCell(1)).getValue());
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
		
		
//		int year = calendar.get(Calendar.YEAR);
//		int month = calendar.get(Calendar.MONTH);
//		int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//		resultat += year;
//		resultat += "-";
//
//		month++;
//		if (month < 10) {
//			resultat += "0" + month + "-";
//		} else {
//			resultat += month + "-";
//		}
//
//		if (day < 10) {
//			resultat += "0" + day;
//		} else {
//			resultat += day;
//		}
//
//		return resultat;
	}

	private int getHour(ConfTimeCell cell) {
		Calendar calendar = GregorianCalendar.getInstance();
		if (cell.getValue() == null) {
			return -1;
		}
		calendar.setTime(cell.getValue());

		int hour = calendar.get(Calendar.HOUR_OF_DAY);

		return hour;
	}

}
