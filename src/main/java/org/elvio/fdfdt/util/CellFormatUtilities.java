package org.elvio.fdfdt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

public class CellFormatUtilities {
	
	private static CellStyle timeStyle = null; 
	private static CellStyle dateStyle = null;
	
	public static void getXlsDateValue(HSSFCell cell, Date cellValue) {
		HSSFWorkbook wb = cell.getSheet().getWorkbook();
		initDateStyle(wb);	
		cell.setCellStyle(dateStyle);
		cell.setCellValue(cellValue);
		
//		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
//
//		Calendar calendar = GregorianCalendar.getInstance();
//		calendar.setTime((Date) cellValue);
//		String formula = "TIME(" + calendar.get(Calendar.HOUR_OF_DAY) + "," + calendar.get(Calendar.MINUTE) + ","
//				+ calendar.get(Calendar.SECOND) + ")";
//
//		cell.setCellFormula(formula);
//		cell.setCellType(Cell.CELL_TYPE_FORMULA);
//		cell.setCellStyle(timeStyle);
//		evaluator.evaluateFormulaCell(cell);
		
	}

	public static void getXlsTimeValue(HSSFCell cell, Object cellValue) {
		HSSFWorkbook wb = cell.getSheet().getWorkbook();
		initTimeStyle(wb);	
		
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime((Date) cellValue);
		String formula = "TIME(" + calendar.get(Calendar.HOUR_OF_DAY) + "," + calendar.get(Calendar.MINUTE) + ","
				+ calendar.get(Calendar.SECOND) + ")";

		cell.setCellFormula(formula);
		cell.setCellType(Cell.CELL_TYPE_FORMULA);
		cell.setCellStyle(timeStyle);
		evaluator.evaluateFormulaCell(cell);

	}

	private static void initDateStyle(HSSFWorkbook wb) {
		if(dateStyle == null){
			dateStyle = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			dateStyle.setDataFormat(df.getFormat("d-mmm-yy;@"));
		}
	}
	
	private static void initTimeStyle(HSSFWorkbook wb) {
		if(timeStyle == null){
			timeStyle = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			timeStyle.setDataFormat(df.getFormat("[h]:mm:ss;@"));
		}
	}

	public static String convertColumnIndex(int index) {
		switch (index) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		case 4:
			return "E";
		case 5:
			return "F";
		case 6:
			return "G";
		case 7:
			return "H";
		case 8:
			return "I";
		case 9:
			return "J";
		case 10:
			return "K";
		case 11:
			return "L";
		case 12:
			return "M";
		case 13:
			return "N";
		case 14:
			return "O";
		case 15:
			return "P";
		default:
			return "ZZ";
		}
	}

	private static String convertXslCellDateToString(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		return sf.format(date);
	}

	public static String toString(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {
			return (CellFormatUtilities.convertXslCellDateToString(cell.getDateCellValue()));
		} else {
			return (cell.toString());
		}
	}

}
