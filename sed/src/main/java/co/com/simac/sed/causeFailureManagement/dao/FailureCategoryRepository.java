package co.com.simac.sed.causeFailureManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.causeFailureManagement.model.FailureCategory;



/**
 * 
 * @author Maria Perdomo SIMAC
 *
 */
@Repository
public interface FailureCategoryRepository extends
		JpaRepository<FailureCategory, Long> {
}
