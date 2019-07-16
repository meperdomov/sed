package co.com.simac.sed.causeFailureManagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import co.com.simac.sed.causeFailureManagement.dao.FailureCategoryRepository;
import co.com.simac.sed.causeFailureManagement.model.FailureCategory;

/**
 * 
 * @author Maria Perdomo SIMAC
 *
 */
@Component
public class ConfigurationFailureCategory implements ApplicationRunner {

	@Autowired
	private FailureCategoryRepository failureCategoryRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/* Se crean categorias de falla por defecto */
		FailureCategory failureCategory1 = new FailureCategory();
		failureCategory1.setId(new Long(1));
		failureCategory1.setName("Falla de equipo");

		failureCategoryRepository.save(failureCategory1);

		FailureCategory failureCategory2 = new FailureCategory();
		failureCategory2.setId(new Long(2));
		failureCategory2.setName("Falla de materia prima");

		failureCategoryRepository.save(failureCategory2);

		FailureCategory failureCategory3 = new FailureCategory();
		failureCategory3.setId(new Long(3));
		failureCategory3.setName("Falla en planificación");

		failureCategoryRepository.save(failureCategory3);

		FailureCategory failureCategory4 = new FailureCategory();
		failureCategory4.setId(new Long(4));
		failureCategory4.setName("Falla de personal");

		failureCategoryRepository.save(failureCategory4);

	}

}
