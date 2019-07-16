package co.com.simac.sed.auxiliaryservicesmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.auxiliaryservicesmanagement.model.AuxiliaryService;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface AuxiliaryServiceRepository extends
		JpaRepository<AuxiliaryService, Long> {

}
