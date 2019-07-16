package co.com.simac.sed.cube.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.Activity;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	
	@Query(value = "SELECT DISTINCT abreviacion_rb as etapa "
			+ "FROM actividad", nativeQuery = true)
	public List<String> findStageByActivity();
	
	@Query(value="select * from actividad "
			+ "inner join equipamiento on equipamiento.id=equipo_id "
			+ "where orden_id= :orderId", nativeQuery = true)
	public List<Activity> findActivitiesByOrder(@Param("orderId") Long orderId);
}
