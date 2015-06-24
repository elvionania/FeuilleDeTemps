package org.elvio.fdfdt;

import static org.elvio.fdfdt.business.process.FilterFactory.EMPLOYEE;
import static org.elvio.fdfdt.business.process.FilterFactory.MAPPING;
import static org.elvio.fdfdt.business.process.FilterFactory.PLANNING;

import java.util.ArrayList;
import java.util.List;

import org.elvio.fdfdt.business.process.Transformateur;
import org.elvio.fdfdt.file.conf.FdtConfiguration;
import org.elvio.fdfdt.file.conf.HoraireConfiguration;
import org.elvio.fdfdt.file.conf.meta.Information;
import org.elvio.fdfdt.file.io.IoXsl;


public class App 
{
    private static final String FILE_EXTENSION = "xls";
	private static final String FILE_NAME = "resultat";
	private static final String SRC_FILE = "paie 9 juin.xls";
	private static final String CFG_FILE = "horaires.xls";

	public static void main( String[] args )
    {
        Information employeesInfo = new Information();
        Information employeesHoraires = new Information();
        
        List<String> dataFilters = new ArrayList<String>();
        dataFilters.add(EMPLOYEE);
        dataFilters.add(MAPPING);

        List<String> planningFilters = new ArrayList<String>();
        planningFilters.add(PLANNING);
        
        employeesInfo.add(FdtConfiguration.class.getName(), new FdtConfiguration());
        Transformateur.extractData(employeesInfo, FdtConfiguration.class.getName(), IoXsl.importXsl(SRC_FILE));
        
        employeesHoraires.add(HoraireConfiguration.class.getName(), new HoraireConfiguration());
        Transformateur.extractData(employeesHoraires, HoraireConfiguration.class.getName(), IoXsl.importXsl(CFG_FILE));
        
        employeesInfo.addDependancy(PLANNING, employeesHoraires);
        
        IoXsl.exportXsl(FILE_NAME,FILE_EXTENSION, Transformateur.processData(employeesInfo, dataFilters));
    }
}
