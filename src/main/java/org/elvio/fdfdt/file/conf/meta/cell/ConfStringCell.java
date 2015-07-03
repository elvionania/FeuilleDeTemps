package org.elvio.fdfdt.file.conf.meta.cell;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

public class ConfStringCell extends ConfCell<String> {

	public ConfStringCell(String name){
		this.name = name;
	}
	
	public ConfStringCell(String name, String value){
		this.name = name;
		this.value = value;
	}

	public ConfStringCell(){
	}
	
	public String getValue() {
		return (String) value;
	}

	public void process(HSSFCell xslCell) {
		xslCell.setCellType(Cell.CELL_TYPE_STRING);
		xslCell.setCellValue(getValue());
	}

	@Override
	public void extract(String value) {
		this.value = value;
	}

}
