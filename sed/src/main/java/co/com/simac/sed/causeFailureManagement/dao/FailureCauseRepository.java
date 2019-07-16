package co.com.simac.sed.causeFailureManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.causeFailureManagement.model.FailureCause;



/**
 * 
 * @author Juan Camilo
 *
 */
@Repository
public interface FailureCauseRepository extends JpaRepository<FailureCause, Long> {

}
