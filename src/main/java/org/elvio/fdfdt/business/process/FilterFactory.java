package org.elvio.fdfdt.business.process;

import org.elvio.fdfdt.business.rules.EmployeFiltre;
import org.elvio.fdfdt.business.rules.Filter;
import org.elvio.fdfdt.business.rules.MappingFiltre;
import org.elvio.fdfdt.business.rules.PlanningFiltre;

public class FilterFactory {

	public static final String MAPPING = "mapping";
	public static final String EMPLOYEE = "employee";
	public static final String PLANNING = "planning";

	public static Filter getInstance(String filterName) {
		Filter filter;
		switch(filterName){
			case EMPLOYEE	: filter = new EmployeFiltre();break;
			case MAPPING 	: filter = new MappingFiltre();break;
			case PLANNING 	: filter = new PlanningFiltre();break;
			default 		: filter = null;
		}

		return filter;
	}
}
