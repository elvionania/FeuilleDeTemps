package org.elvio.fdfdt.file.conf.meta.row;

import java.util.ArrayList;
import java.util.List;

import org.elvio.fdfdt.file.conf.meta.cell.Cell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfCell;

public abstract class ConfRow {

	protected List<Cell> cells;
	protected String type;
	
	protected final static String HEAD = "head";
	protected final static String BODY = "body";
	protected final static String FOOTER = "footer";
	
	
	public ConfRow() {
		cells = new ArrayList<Cell>();
	}

	public void addConfCell(Cell confCell) {
		cells.add(confCell);
	}
	
	@SuppressWarnings("unchecked")
	public <T> void setCellValue(int position, T value) {
		((ConfCell<T>)cells.get(position)).setValue(value);
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public abstract Cell getCell(String name);

	public abstract int getCount();

	public abstract List<ConfRow> getRows();

	public abstract ConfRow getCurrentRow();

	public Cell getCell(int cCount) {
		return cells.get(cCount);
	}
	
	public boolean isHeader(){
		return HEAD.equals(type);
	}

	public boolean isBody(){
		return BODY.equals(type);
	}
	
	public boolean isFooter(){
		return FOOTER.equals(type);
	}
}
