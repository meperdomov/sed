package co.com.simac.sed.cube.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.Measurer;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface MeasurerRepository extends JpaRepository<Measurer, Long> {

	@Query(value = "SELECT m.* FROM medidor m INNER JOIN equipamiento AS e ON e.id = m.equipo_id  where  m.equipo_id= :equipmentId", nativeQuery = true)
	public List<Measurer> findMeasurerByEquipment(
			@Param("equipmentId") Long equipmentId);

}