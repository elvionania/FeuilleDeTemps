package org.elvio.fdfdt.file.conf.meta;

import java.util.ArrayList;
import java.util.List;

import org.elvio.fdfdt.file.conf.meta.cell.Cell;
import org.elvio.fdfdt.file.conf.meta.row.BodyRow;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;
import org.elvio.fdfdt.file.conf.meta.row.HeaderRow;

public abstract class ClassConfiguration {

	protected List<ConfRow> configuration;

	public ClassConfiguration() {
		super();
	}

//	public void process(List<List<String>> data) {
//
//		BodyRow iterativeRow;
//		ConfRow currentRow;
//		for (int i = 0; i < data.size(); i++) {
//
//			if (isHeader(i)) {
//				currentRow = configuration.get(i);
//				for (int j = 0; j < data.get(i).size(); j++) {
//					currentRow.getCell(j).extract(data.get(i).get(j));
//				}
//			} else if (isBody(i)) {
//				iterativeRow = ((BodyRow) configuration.get(configuration.size() - 1));
//				iterativeRow.addNewRow();
//				currentRow = iterativeRow.getCurrentRow();
//				for (int j = 0; j < data.get(i).size(); j++) {
//					currentRow.getCell(j).extract(data.get(i).get(j));
//				}
//			}
//		}
//	}
	
	public ConfRow get(int index){
		return configuration.get(index);
	}
	
	public int size(){
		return configuration.size();
	}

	public boolean isHeader(int lineNbr) {
		return (lineNbr < configuration.size() && configuration.get(lineNbr) instanceof HeaderRow);
	}

	public boolean isBody(int lineNbr) {
		return (configuration.get(configuration.size() - 1) instanceof BodyRow && lineNbr >= configuration.size() - 1);
	}

	public List<ConfRow> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(List<ConfRow> configuration) {
		this.configuration = configuration;
	}

	public void addRow(ConfRow row) {
		configuration.add(row);
	}

	public void addRows(List<ConfRow> value) {
		configuration.addAll(value);
	}

	public List<ConfRow> getRows() {
		List<ConfRow> rows = new ArrayList<ConfRow>();
		for (ConfRow row : this.configuration) {
			rows.addAll(row.getRows());
		}
		return rows;
	}

	public int countRows() {
		int result = 0;

		for (ConfRow row : configuration) {
			result += row.getCount();
		}

		return result;
	}

	public ConfRow getRow(int i) {
		int count = 0;
		ConfRow result = null;
		for (ConfRow row : configuration) {
			if (count > i) {
				break;
			}
			count += row.getCount();
			if (count >= i) {
				count -= row.getCount();
				for (ConfRow innerRow : row.getRows()) {
					if (count++ == i) {
						return innerRow;
					}
				}
			}
		}
		return result;
	}

	public int cellSize(int rCount) {
		return getRow(rCount).getCells().size();
	}

	public Object getValue(int rCount, int cCount) {
		if (getRow(rCount) == null) {
		} else if (getRow(rCount).getCell(cCount) == null) {
		} else if (getRow(rCount).getCell(cCount).getValue() == null) {
		} else {
			return getRow(rCount).getCell(cCount).getValue();
		}
		return "";
	}

	public ConfRow getRow(String cellName4Row, String cellValue4Row) {
		for (ConfRow row : getRows()) {
			if (row.getCell(cellName4Row) != null
					&& row.getCell(cellName4Row).getValue().toString().equalsIgnoreCase(cellValue4Row)) {
				return row;
			}
		}
		return null;
	}

	public Cell getCell(int rCount, int cCount) {
		if (getRow(rCount) == null) {
		} else if (getRow(rCount).getCell(cCount) == null) {
		} else {
			return getRow(rCount).getCell(cCount);
		}
		return null;
	}

}