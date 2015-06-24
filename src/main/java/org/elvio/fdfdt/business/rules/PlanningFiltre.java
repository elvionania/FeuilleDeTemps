package org.elvio.fdfdt.business.rules;

import static org.elvio.fdfdt.file.conf.FdtConfiguration.NOM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elvio.fdfdt.file.conf.EmployeConfiguration;
import org.elvio.fdfdt.file.conf.HoraireConfiguration;
import org.elvio.fdfdt.file.conf.meta.Information;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;

public class PlanningFiltre implements Filter {

	@Override
	public Information applyFilter(Information configuration) {
		Information info = new Information();

		Map<String, List<ConfRow>> employes = new HashMap<String, List<ConfRow>>();
		List<ConfRow> rows;

		for (ConfRow row : configuration.getRows(HoraireConfiguration.class.getName())) {

			// une ligne un horaire

			if (row.getCell(NOM) != null) {
				rows = employes.get(row.getCell(NOM).getValue().toString());
				if (rows == null) {
					rows = new ArrayList<ConfRow>();
					employes.put(row.getCell(NOM).getValue().toString(), rows);
				}
				rows.add(row);
			}
		}

		EmployeConfiguration config;

		for (Map.Entry<String, List<ConfRow>> entry : employes.entrySet()) {
			config = new EmployeConfiguration();
			config.addRows(entry.getValue());
			info.add(entry.getKey(), config);
		}

		return info;
	}

}
