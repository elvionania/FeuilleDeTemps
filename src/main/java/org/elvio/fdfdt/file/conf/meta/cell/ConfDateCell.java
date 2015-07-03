package org.elvio.fdfdt.file.conf.meta.cell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.elvio.fdfdt.util.CellFormatUtilities;

public class ConfDateCell extends ConfCell<Date> {

	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public ConfDateCell(String name) {
		this.name = name;
	}

	public ConfDateCell() {
	}

	public void process(HSSFCell xslCell) {
		if(getValue() != null){
			CellFormatUtilities.getXlsDateValue(xslCell, getValue());
		}
	}

	@Override
	public void extract(String value) {
		try {
			this.setValue(sf.parse(value.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
