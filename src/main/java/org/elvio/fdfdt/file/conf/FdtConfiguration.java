package org.elvio.fdfdt.file.conf;

import java.util.ArrayList;

import org.elvio.fdfdt.file.conf.meta.ClassConfiguration;
import org.elvio.fdfdt.file.conf.meta.cell.ConfDateCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfNumberCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfStringCell;
import org.elvio.fdfdt.file.conf.meta.row.BodyRow;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;
import org.elvio.fdfdt.file.conf.meta.row.HeaderRow;

public class FdtConfiguration extends ClassConfiguration {

	private static final String NUMERO_DE_CARTE = "numero de carte";
	public static final String NOM = "nom";
	public static final String TYPE_EVENEMENT = "type evenement";
	public static final String DATE = "date";

	public FdtConfiguration() {
		configuration = new ArrayList<ConfRow>();

		HeaderRow row0 = new HeaderRow();
		row0.addConfCell(new ConfStringCell());
		row0.addConfCell(new ConfStringCell());
		row0.addConfCell(new ConfDateCell());
		configuration.add(row0);

		HeaderRow row1 = new HeaderRow();
		row1.addConfCell(new ConfStringCell());
		configuration.add(row1);

		HeaderRow row2 = new HeaderRow();
		row2.addConfCell(new ConfStringCell());
		row2.addConfCell(new ConfStringCell());
		configuration.add(row2);

		HeaderRow row3 = new HeaderRow();
		row3.addConfCell(new ConfStringCell());
		row3.addConfCell(new ConfStringCell());
		configuration.add(row3);

		HeaderRow row4 = new HeaderRow();
		row4.addConfCell(new ConfStringCell());
		row4.addConfCell(new ConfStringCell());
		configuration.add(row4);

		HeaderRow row5 = new HeaderRow();
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		row5.addConfCell(new ConfStringCell());
		configuration.add(row5);

		BodyRow rows = new BodyRow();
		rows.addConfCell(new ConfNumberCell());
		rows.addConfCell(new ConfDateCell(DATE));
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell(TYPE_EVENEMENT));
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell(NOM));
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell(NUMERO_DE_CARTE));
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		configuration.add(rows);
	}

}
