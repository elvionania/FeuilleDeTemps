package org.elvio.fdfdt.file.conf.meta.row;

import java.util.ArrayList;
import java.util.List;

import org.elvio.fdfdt.file.conf.meta.cell.Cell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfDateCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfFormulaDateCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfNumberCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfStringCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfTimeCell;

public class BodyRow extends ConfRow {

	private List<ConfRow> rows;
	private HeaderRow currentRow;

	public BodyRow() {
		rows = new ArrayList<ConfRow>();
	}

	public void addNewRow() {
		currentRow = new HeaderRow();
		for (Cell cell : this.getCells()) {
			if (cell instanceof ConfStringCell) {
				currentRow.addConfCell(new ConfStringCell(cell.getName()));
			} else if (cell instanceof ConfTimeCell) {
				currentRow.addConfCell(new ConfTimeCell(cell.getName()));
			} else if (cell instanceof ConfNumberCell) {
				currentRow.addConfCell(new ConfNumberCell(cell.getName()));
			} else if (cell instanceof ConfFormulaDateCell) {
				currentRow.addConfCell(new ConfFormulaDateCell(cell.getName()));
			} else if (cell instanceof ConfDateCell) {
				currentRow.addConfCell(new ConfDateCell(cell.getName()));
			}
		}
		rows.add(currentRow);
	}

	public void setCellValue(int position, Object value) {
		currentRow.setCellValue(position, value);
	}

	public Cell getCell(String name) {
		for (Cell cell : currentRow.getCells()) {
			if (cell.getName().equalsIgnoreCase(name)) {
				return cell;
			}
		}
		return null;
	}

	@Override
	public int getCount() {
		return rows.size();
	}

	@Override
	public List<ConfRow> getRows() {
		return rows;
	}

	@Override
	public ConfRow getCurrentRow() {
		return currentRow;
	}

}
