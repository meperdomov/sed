package co.com.simac.sed.general.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import co.com.simac.sed.enums.Parameter;
import co.com.simac.sed.general.dao.ParameterConfigurationRepository;
import co.com.simac.sed.general.model.ParameterConfiguration;

/**
 * 
 * @author Juan Camilo
 *
 */
@Component
public class ConfigurationParameter implements ApplicationRunner {

	@Autowired
	private ParameterConfigurationRepository parameterConfigurationRepository;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {

		/* Se crea el parametro PORCENTAJE_ORDENES_FALLIDAS */

		ParameterConfiguration parameter1 =  new ParameterConfiguration();
		parameter1.setKey(Parameter.PORCENTAJE_ORDENES_FALLIDAS.toString());
		parameter1.setValue("5");
		parameterConfigurationRepository.save(parameter1);
	
	}
}
