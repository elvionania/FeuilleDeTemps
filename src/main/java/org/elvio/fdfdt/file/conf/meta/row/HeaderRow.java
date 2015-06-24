package org.elvio.fdfdt.file.conf.meta.row;

import java.util.ArrayList;
import java.util.List;

import org.elvio.fdfdt.file.conf.meta.cell.Cell;

public class HeaderRow extends ConfRow {
	 
	public Cell getCell(String name){
		for(Cell cell : cells){
			if(cell.getName().equalsIgnoreCase(name)){
				return cell;
			}
		}
		return null;
	}

	@Override
	public int getCount() {
		return 1;
	}

	@Override
	public List<ConfRow> getRows() {
		List<ConfRow> rows = new ArrayList<ConfRow>();
		rows.add(this);
		return rows;
	}

	@Override
	public ConfRow getCurrentRow() {
		return this;
	}
}
