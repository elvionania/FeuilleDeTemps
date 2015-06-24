package org.elvio.fdfdt.business.process;

import java.util.List;

import org.elvio.fdfdt.business.rules.Filter;
import org.elvio.fdfdt.file.conf.meta.ClassConfiguration;
import org.elvio.fdfdt.file.conf.meta.Information;
import org.elvio.fdfdt.file.conf.meta.row.BodyRow;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;

public class Transformateur {
	
	public static Information processData(Information info, List<String> filterList) {

		Information resultat = new Information(info);
		Filter filter;

		for (String filterName : filterList) {
			filter = FilterFactory.getInstance(filterName);
			resultat = filter.applyFilter(resultat);
		}
		
		return resultat;
	}
	
	public static void extractData(Information info, Object configKey, List<List<String>> data){
		BodyRow iterativeRow;
		ConfRow currentRow;
		ClassConfiguration configuration = info.getConfigurations().get(configKey);
		
		for (int i = 0; i < data.size(); i++) {

			if (configuration.isHeader(i)) {
				currentRow = configuration.get(i);
				for (int j = 0; j < data.get(i).size(); j++) {
					currentRow.getCell(j).extract(data.get(i).get(j));
				}
			} else if (configuration.isBody(i)) {
				iterativeRow = ((BodyRow) configuration.get(configuration.size() - 1));
				iterativeRow.addNewRow();
				currentRow = iterativeRow.getCurrentRow();
				for (int j = 0; j < data.get(i).size(); j++) {
					currentRow.getCell(j).extract(data.get(i).get(j));
				}
			}
		}
	}

}
