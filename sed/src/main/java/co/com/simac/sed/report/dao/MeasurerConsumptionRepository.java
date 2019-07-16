package co.com.simac.sed.report.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.MeasurerConsumption;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface MeasurerConsumptionRepository extends
		JpaRepository<MeasurerConsumption, Long> {

	@Query(value = "SELECT * FROM consumo_medidor as t1 "
			+ "INNER JOIN medidor as t2 on t1.medidor_id=t2.id "
			+ "INNER JOIN equipamiento as t3 ON t2.equipamiento_id=t3.id "
			+ "WHERE  t1.fecha_inicio >= :initialDate and t1.fecha_fin <= :finalDate and t1.variable_id= :variableId  and t3.consume_por_actividad=false", nativeQuery = true)
	public List<MeasurerConsumption> findMeasurerConsumptionIndirect(
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate,
			@Param("variableId") Long variableId
			);
	
	
	@Query(value = "SELECT cm.* FROM consumo_medidor cm "
			+ "INNER JOIN medidor AS m ON m.id = cm.medidor_id  where  cm.fecha_inicio>= :startDate and cm.fecha_inicio <= :endDate", nativeQuery = true)
	public List<MeasurerConsumption> findMeasurerConsumptionRange(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
