package org.elvio.fdfdt.file.conf;

import java.util.ArrayList;

import org.elvio.fdfdt.file.conf.meta.ClassConfiguration;
import org.elvio.fdfdt.file.conf.meta.cell.ConfDateCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfNumberCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfStringCell;
import org.elvio.fdfdt.file.conf.meta.row.BodyRow;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;

public class EmployeConfiguration extends ClassConfiguration {

	public EmployeConfiguration(){
		configuration = new ArrayList<ConfRow>();
				
		BodyRow rows = new BodyRow();
		rows.addConfCell(new ConfNumberCell("sequence"));
		rows.addConfCell(new ConfDateCell("date"));
		rows.addConfCell(new ConfStringCell("acces"));
		rows.addConfCell(new ConfStringCell("typeAcces"));
		rows.addConfCell(new ConfStringCell("code1"));
		rows.addConfCell(new ConfStringCell("porte"));
		rows.addConfCell(new ConfStringCell("code2"));
		rows.addConfCell(new ConfStringCell("nom"));
		rows.addConfCell(new ConfStringCell("code3"));
		rows.addConfCell(new ConfStringCell("code4"));
		rows.addConfCell(new ConfStringCell("carte"));
		configuration.add(rows);
	}
	
}
