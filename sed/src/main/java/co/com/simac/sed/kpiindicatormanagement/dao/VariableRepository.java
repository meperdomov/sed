package co.com.simac.sed.kpiindicatormanagement.dao;







import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import co.com.simac.sed.kpiindicatormanagement.model.Variable;




/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface VariableRepository extends JpaRepository<Variable, Long> {
	
	
}
