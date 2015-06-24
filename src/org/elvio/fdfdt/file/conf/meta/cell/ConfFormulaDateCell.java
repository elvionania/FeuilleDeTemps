package org.elvio.fdfdt.file.conf.meta.cell;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;


public class ConfFormulaDateCell extends ConfCell<String> {

	public ConfFormulaDateCell(String name){
		this.name = name;
	}
		
	public String getValue() {
		return (String) value;
	}
	
	@Override
	public void process(HSSFCell xslCell) {
		
		CellStyle style = xslCell.getRow().getSheet().getWorkbook().createCellStyle();
        DataFormat df = xslCell.getRow().getSheet().getWorkbook().createDataFormat();
        style.setDataFormat(df.getFormat("[h]:mm:ss;@"));
		
		xslCell.setCellStyle(style);
		xslCell.setCellType(Cell.CELL_TYPE_NUMERIC);
		xslCell.setCellFormula(value);
	}

	@Override
	public void extract(String value) {
		this.value = value;
	}
}
