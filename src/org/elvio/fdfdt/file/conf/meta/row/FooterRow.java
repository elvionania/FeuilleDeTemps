package org.elvio.fdfdt.file.conf.meta.row;

import java.util.List;

import org.elvio.fdfdt.file.conf.meta.cell.Cell;

public class FooterRow extends ConfRow {

	@Override
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ConfRow> getRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfRow getCurrentRow() {
		// TODO Auto-generated method stub
		return null;
	}

}
