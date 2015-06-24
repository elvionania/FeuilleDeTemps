package org.elvio.fdfdt.file.conf.meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elvio.fdfdt.file.conf.meta.cell.Cell;
import org.elvio.fdfdt.file.conf.meta.row.ConfRow;

public class Information {

	private Map<String, ClassConfiguration> configurations;
	private Map<String, Information> dependancies; 

	public Map<String, Information> getDependancies() {
		return dependancies;
	}

	public void setDependancies(Map<String, Information> dependancies) {
		this.dependancies = dependancies;
	}

	public Information(Information info) {
		this.configurations = info.getConfigurations();
		this.dependancies = info.dependancies;
	}

	public Information() {
		this.configurations = new HashMap<String, ClassConfiguration>();
	}

	public Map<String, ClassConfiguration> getConfigurations() {
		return configurations;
	}
		
	public void add(String name, ClassConfiguration config) {
		if (configurations == null) {
			configurations = new HashMap<String, ClassConfiguration>();
		}
		
		configurations.put(name, config);
	}

	public int countRows() {
		int result = 0;

		for (Map.Entry<String, ClassConfiguration> entry : configurations.entrySet()) {
				result += entry.getValue().countRows();
		}
		return result;
	}

//	public void process(String name, List<List<String>> getvalues) {
//		configurations.get(name).process(getvalues);
//	}

	public List<ConfRow> getRows(String name) {
		return configurations.get(name).getRows();
	}
	
	public void addDependancy(String dependancyName, Information dependancy){
		if(dependancies == null){
			dependancies = new HashMap<String, Information>();
		}
		this.dependancies.put(dependancyName, dependancy);
	}
	
	public Cell getDependancyEntry(String dependancyName, String configurationName, String cellName4Row, String cellValue4Row, String cellName){
		ConfRow row = this.dependancies.get(dependancyName).getConfigurations().get(configurationName).getRow(cellName4Row, cellValue4Row);
		if(row == null) return null;
		return row.getCell(cellName);
	}

}
