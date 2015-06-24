package org.elvio.fdfdt.file.conf.meta.cell;

import org.apache.poi.hssf.usermodel.HSSFCell;

public interface Cell {

	Object getValue();

	String getName();
	
	void process(HSSFCell xslCell);

	void extract(String value);
	
}
