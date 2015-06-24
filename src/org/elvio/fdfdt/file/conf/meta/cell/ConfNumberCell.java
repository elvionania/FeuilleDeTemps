package org.elvio.fdfdt.file.conf.meta.cell;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

public class ConfNumberCell extends ConfCell<Double> {
	
	public ConfNumberCell(String name) {
		this.name = name;
	}

	public ConfNumberCell() {
		// TODO Auto-generated constructor stub
	}

	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public void setValue(String value){
		if(value.toString().length()==0){
			this.value = 0d;
		}
		try{
			this.value = Double.valueOf(value.toString());
		}catch(ClassCastException e){
			System.out.println("ccexception "+value.toString());
		}
	}
	
	public void process(HSSFCell xslCell) {
		if(getValue() != null){
			xslCell.setCellType(Cell.CELL_TYPE_NUMERIC);
			xslCell.setCellValue(getValue());
		}
	}

	@Override
	public void extract(String value) {
		this.value = Double.parseDouble(value.toString());
	}
	
}
