package org.elvio.fdfdt.business.rules;

import static org.elvio.fdfdt.file.conf.FdtConfiguration.TYPE_EVENEMENT;

import org.elvio.fdfdt.file.conf.FdtConfiguration;
import org.elvio.fdfdt.file.conf.meta.ClassConfiguration;
import org.elvio.fdfdt.file.conf.meta.Information;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;

public class EvenementFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.elvio.fdfdt.business.rules.Filtre#applyFilter(org.elvio.fdfdt.file
	 * .conf.meta.ClassConfiguration)
	 */
	public Information applyFilter(Information configuration) {

		Information info = new Information();
		ClassConfiguration unite = new FdtConfiguration();

		for (ConfRow row : configuration.getRows(FdtConfiguration.class.getName())) {
			if (row.getCell(TYPE_EVENEMENT) != null && !row.getCell(TYPE_EVENEMENT).getValue().equals("200")) {
				unite.addRow(row);
			}
			if (row.getCell(TYPE_EVENEMENT) == null) {
				unite.addRow(row);
			}
		}

		info.add(FdtConfiguration.class.getName(), unite);

		return info;
	}

}
