package co.com.simac.sed.general.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.general.model.ParameterConfiguration;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface ParameterConfigurationRepository extends JpaRepository<ParameterConfiguration, Long> {

	ParameterConfiguration findByKey(String key);
	
}