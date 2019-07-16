package co.com.simac.sed.kpiindicatormanagement.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.Equipment;


/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

	
	@Query("from Equipment e where e.parent IS NULL")
	public List<Equipment> findAllParents();
	
	public List<Equipment> findByParent(Equipment parent);
	
}
