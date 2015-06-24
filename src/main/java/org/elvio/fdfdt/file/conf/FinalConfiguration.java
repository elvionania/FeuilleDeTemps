package org.elvio.fdfdt.file.conf;

import java.util.ArrayList;

import org.elvio.fdfdt.file.conf.meta.ClassConfiguration;
import org.elvio.fdfdt.file.conf.meta.cell.ConfDateCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfFormulaDateCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfNumberCell;
import org.elvio.fdfdt.file.conf.meta.cell.ConfStringCell;
import org.elvio.fdfdt.file.conf.meta.row.BodyRow;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;
import org.elvio.fdfdt.file.conf.meta.row.FooterRow;
import org.elvio.fdfdt.file.conf.meta.row.HeaderRow;

public class FinalConfiguration extends ClassConfiguration {

	public static final String RETARD = "retard";
	public static final String TOTAL_A_PAYER = "total a payer";
	public static final String NOUVEAU_CREDIT_BANQUE = "nouveau credit banque";
	public static final String ANCIEN_CREDIT_BANQUE = "ancien credit banque";
	public static final String TOTAL_MOIS = "total mois";
	public static final String TOTAL_SEMAINE_4 = "total semaine 4";
	public static final String TOTAL_SEMAINE_3 = "total semaine 3";
	public static final String TOTAL_SEMAINE_2 = "total semaine 2";
	public static final String TOTAL_SEMAINE_1 = "total semaine 1";
	public static final String DIFFERENCE = "difference";
	public static final String DUREE_DE_PAUSE_REELLE = "duree de pause reelle";
	public static final String DUREE_DE_PAUSE_PREVUE = "duree de pause prevue";
	public static final String HEURE_REELLE_DE_FIN = "heure reelle de fin";
	public static final String HEURE_PREVUE_DE_FIN = "heure prevue de fin";
	public static final String HEURE_REELLE_DE_DEBUT = "heure reelle de debut";
	public static final String HEURE_PREVUE_DE_DEBUT = "heure prevue de debut";
	public static final String NOM_DE_L_EDUCATRICE = "nom de l educatrice";
	public static final String NUMERO_DE_CARTE = "numero de carte";
	public static final String SEQUENCE_FIN = "sequence Fin";
	public static final String SEQUENCE_DEBUT = "sequence Debut";
	public static final String DATE = "date";

	public FinalConfiguration(){
		configuration = new ArrayList<ConfRow>();
			
		HeaderRow row0 = new HeaderRow();
		row0.addConfCell(new ConfStringCell(DATE, DATE));
		row0.addConfCell(new ConfStringCell(SEQUENCE_DEBUT, SEQUENCE_DEBUT));
		row0.addConfCell(new ConfStringCell(SEQUENCE_FIN, SEQUENCE_FIN));
		row0.addConfCell(new ConfStringCell(NUMERO_DE_CARTE, NUMERO_DE_CARTE));
		row0.addConfCell(new ConfStringCell(NOM_DE_L_EDUCATRICE, NOM_DE_L_EDUCATRICE));
		row0.addConfCell(new ConfStringCell(HEURE_PREVUE_DE_DEBUT, HEURE_PREVUE_DE_DEBUT));
		row0.addConfCell(new ConfStringCell(HEURE_REELLE_DE_DEBUT, HEURE_REELLE_DE_DEBUT));
		row0.addConfCell(new ConfStringCell(HEURE_PREVUE_DE_FIN, HEURE_PREVUE_DE_FIN));
		row0.addConfCell(new ConfStringCell(HEURE_REELLE_DE_FIN, HEURE_REELLE_DE_FIN));
		row0.addConfCell(new ConfStringCell(DUREE_DE_PAUSE_PREVUE, DUREE_DE_PAUSE_PREVUE));
		row0.addConfCell(new ConfStringCell(DUREE_DE_PAUSE_REELLE, DUREE_DE_PAUSE_REELLE));
		row0.addConfCell(new ConfStringCell(DIFFERENCE, DIFFERENCE));
		row0.addConfCell(new ConfStringCell(TOTAL_SEMAINE_1, TOTAL_SEMAINE_1));
		row0.addConfCell(new ConfStringCell(TOTAL_SEMAINE_2, TOTAL_SEMAINE_2));
		row0.addConfCell(new ConfStringCell(TOTAL_SEMAINE_3, TOTAL_SEMAINE_3));
		row0.addConfCell(new ConfStringCell(TOTAL_SEMAINE_4, TOTAL_SEMAINE_4));
		row0.addConfCell(new ConfStringCell(TOTAL_MOIS, TOTAL_MOIS));
		row0.addConfCell(new ConfStringCell(ANCIEN_CREDIT_BANQUE, ANCIEN_CREDIT_BANQUE));
		row0.addConfCell(new ConfStringCell(NOUVEAU_CREDIT_BANQUE, NOUVEAU_CREDIT_BANQUE));
		row0.addConfCell(new ConfStringCell(TOTAL_A_PAYER, TOTAL_A_PAYER));
		row0.addConfCell(new ConfStringCell(RETARD, RETARD));
		configuration.add(row0);
		
		BodyRow rows = new BodyRow();
		rows.addConfCell(new ConfStringCell(DATE));
		rows.addConfCell(new ConfNumberCell(SEQUENCE_DEBUT));
		rows.addConfCell(new ConfNumberCell(SEQUENCE_FIN));
		rows.addConfCell(new ConfStringCell(NUMERO_DE_CARTE));
		rows.addConfCell(new ConfStringCell(NOM_DE_L_EDUCATRICE));
		rows.addConfCell(new ConfDateCell(HEURE_PREVUE_DE_DEBUT));
		rows.addConfCell(new ConfDateCell(HEURE_REELLE_DE_DEBUT));
		rows.addConfCell(new ConfDateCell(HEURE_PREVUE_DE_FIN));
		rows.addConfCell(new ConfDateCell(HEURE_REELLE_DE_FIN));
		rows.addConfCell(new ConfDateCell(DUREE_DE_PAUSE_PREVUE));
		rows.addConfCell(new ConfDateCell(DUREE_DE_PAUSE_REELLE));
		rows.addConfCell(new ConfFormulaDateCell(DIFFERENCE));
		rows.addConfCell(new ConfNumberCell(TOTAL_SEMAINE_1));
		rows.addConfCell(new ConfNumberCell(TOTAL_SEMAINE_2));
		rows.addConfCell(new ConfNumberCell(TOTAL_SEMAINE_3));
		rows.addConfCell(new ConfNumberCell(TOTAL_SEMAINE_4));
		rows.addConfCell(new ConfNumberCell(TOTAL_MOIS));
		rows.addConfCell(new ConfNumberCell(ANCIEN_CREDIT_BANQUE));
		rows.addConfCell(new ConfNumberCell(NOUVEAU_CREDIT_BANQUE));
		rows.addConfCell(new ConfNumberCell(TOTAL_A_PAYER));
		rows.addConfCell(new ConfDateCell(RETARD));
		
		configuration.add(rows);
		
		FooterRow footer = new FooterRow();
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		rows.addConfCell(new ConfStringCell());
		
		configuration.add(footer);
	}
}
