package org.elvio.fdfdt.file.conf;

import java.util.ArrayList;

import org.elvio.fdfdt.file.conf.meta.ClassConfiguration;
import org.elvio.fdfdt.file.conf.meta.cell.ConfNumberCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfStringCell;
import org.elvio.fdfdt.file.conf.meta.row.BodyRow;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;
import org.elvio.fdfdt.file.conf.meta.row.HeaderRow;

public class HoraireConfiguration extends ClassConfiguration {

	public static final String PAUSE = "pause";
	public static final String HEURE_FIN = "heure fin";
	public static final String HEURE_DEBUT = "heure debut";
	public static final String NOM = "nom";
	public static final String ENTETE_PAUSE = "entete pause";
	public static final String ENTETE_HEURE_FIN = "entete heure fin";
	public static final String ENTETE_HEURE_DEBUT = "entete heure debut";
	public static final String ENTETE_NOM = "entete nom";

	public HoraireConfiguration() {
		configuration = new ArrayList<ConfRow>();
		
		HeaderRow entete = new HeaderRow();
		entete.addConfCell(new ConfStringCell(ENTETE_NOM));
		entete.addConfCell(new ConfStringCell(ENTETE_HEURE_DEBUT));
		entete.addConfCell(new ConfStringCell(ENTETE_HEURE_FIN));
		entete.addConfCell(new ConfStringCell(ENTETE_PAUSE));
		configuration.add(entete);

		BodyRow employee = new BodyRow();
		employee.addConfCell(new ConfStringCell(NOM));
		employee.addConfCell(new ConfNumberCell(HEURE_DEBUT));
		employee.addConfCell(new ConfNumberCell(HEURE_FIN));
		employee.addConfCell(new ConfNumberCell(PAUSE));
		configuration.add(employee);
	}

}
