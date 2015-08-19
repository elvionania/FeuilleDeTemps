package org.elvio.fdfdt;

import static org.elvio.fdfdt.business.process.FilterFactory.EMPLOYEE;
import static org.elvio.fdfdt.business.process.FilterFactory.MAPPING;
import static org.elvio.fdfdt.business.process.FilterFactory.PLANNING;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	private static final String SRC_FILE = "paie.xls";
	private static final String CFG_FILE = "horaires.xls";
	
	public static File produceResult(File cfg, File data){
		System.out.println("début du traitement des données");
        Information employeesInfo = new Information();
        Information employeesHoraires = new Information();
        
        System.out.println("initialisation des operations");
        List<String> dataFilters = new ArrayList<String>();
        dataFilters.add(EMPLOYEE);
        dataFilters.add(MAPPING);
        
        List<String> planningFilters = new ArrayList<String>();
        planningFilters.add(PLANNING);
        
        employeesInfo.add(FdtConfiguration.class.getName(), new FdtConfiguration());
        System.out.println("extraction des horaires");
        Transformateur.extractData(employeesInfo, FdtConfiguration.class.getName(), IoXsl.importXsl(data));
        System.out.println("extraction des planning");
        employeesHoraires.add(HoraireConfiguration.class.getName(), new HoraireConfiguration());
        Transformateur.extractData(employeesHoraires, HoraireConfiguration.class.getName(), IoXsl.importXsl(cfg));
        
        employeesInfo.addDependancy(PLANNING, employeesHoraires);
        
        System.out.println("conversion des données");
        Information resultat = Transformateur.processData(employeesInfo, dataFilters);
        System.out.println("production du fichier final");
        File file = IoXsl.exportXsl(FILE_NAME,FILE_EXTENSION, resultat);
        System.out.println("fin du traitement des données");
		return file;
	}

	public static void main( String[] args ){
		Path chemin 		= Paths.get("");
        Path srcPath = chemin.toAbsolutePath().getParent().resolve(SRC_FILE);
        Path cfgPath = chemin.toAbsolutePath().getParent().resolve(CFG_FILE);
        
		produceResult(cfgPath.toFile(), srcPath.toFile());
    }
}
