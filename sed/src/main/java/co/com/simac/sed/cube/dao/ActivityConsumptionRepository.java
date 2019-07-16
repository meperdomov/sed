package co.com.simac.sed.cube.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.ActivityConsumption;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

@Repository
public interface ActivityConsumptionRepository extends
		JpaRepository<ActivityConsumption, Long> {

	@Query(value = "SELECT * FROM consumo_actividad INNER JOIN (actividad INNER JOIN orden_produccion ON actividad.orden_id=orden_produccion.id) ON consumo_actividad.actividad_id=actividad.id  WHERE  actividad.fecha_ingreso >= :todayDate and actividad.fecha_ingreso <= :finalDate and consumo_actividad.variable_id= :variableId ORDER BY fecha_ingreso ASC", nativeQuery = true)
	
	public List<ActivityConsumption> getTodayActivities(
			@Param("todayDate") Date todayDate, @Param("finalDate") Date finalDate,
			@Param("variableId") Long variableId);

	@Query(value = "SELECT * FROM consumo_actividad as t1 "
			+ "INNER JOIN actividad as t2 on t1.actividad_id=t2.id "
			+ "INNER JOIN orden_produccion as t3 ON t2.orden_id=t3.id "
			+ "INNER JOIN ruta as t4 ON t2.ruta_id=t4.id "
			+ "WHERE  t2.fecha_ingreso >= :initialDate and t2.fecha_ingreso <= :finalDate  and t1.variable_id= :variableId and t2.ruta_id= :routeId  ORDER BY fecha_ingreso ASC", nativeQuery = true)
	public List<ActivityConsumption> getActivitiesByRoute(
			@Param("routeId") Long routeId,
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate,
			@Param("variableId") Long variableId);

	@Query(value = "SELECT * FROM consumo_actividad as t1 "
			+ "INNER JOIN actividad as t2 on t1.actividad_id=t2.id "
			+ "INNER JOIN orden_produccion as t3 ON t2.orden_id=t3.id "
			+ "WHERE  t2.fecha_ingreso >= :initialDate and t2.fecha_ingreso <= :finalDate and t1.variable_id= :variableId and t2.orden_id= :orderId  ORDER BY fecha_ingreso ASC", nativeQuery = true)
	public List<ActivityConsumption> getActivitiesByOrder(
			@Param("orderId") Long orderId,
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate,
			@Param("variableId") Long variableId);

	@Query(value = "SELECT * FROM consumo_actividad as t1 "
			+ " INNER JOIN actividad as t2 on t1.actividad_id=t2.id "
			+ " INNER JOIN equipamiento as t3 ON t2.equipo_id=t3.id "
			+ " WHERE  t2.fecha_ingreso >= :initialDate and t2.fecha_ingreso <= :finalDate and t1.variable_id= :variableId and t2.equipo_id= :equipmentId  ORDER BY fecha_ingreso ASC", nativeQuery = true)
	public List<ActivityConsumption> getActivitiesByEquipment(
			@Param("equipmentId") Long equipmentId,
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate,
			@Param("variableId") Long variableId);

	@Query(value = "SELECT * FROM consumo_actividad as t1 "
			+ "INNER JOIN actividad as t2 on t1.actividad_id=t2.id "
			+ "INNER JOIN orden_produccion as t3 ON t2.orden_id=t3.id "
			+ "WHERE  t2.fecha_ingreso >= :initialDate and t2.fecha_ingreso <= :finalDate and t1.variable_id= :variableId and t2.equipo_id= :equipmentId and t2.codigoProducto= :productCode ORDER BY fecha_ingreso ASC", nativeQuery = true)
	public List<ActivityConsumption> getActivitiesByEquipmentMaterial(
			@Param("equipmentId") Long equipmentId,
			@Param("productCode") String productCode,
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate,
			@Param("variableId") Long variableId);
	
	@Query(value = "SELECT * FROM consumo_actividad as t1 "
			+ " INNER JOIN actividad as t2 on t1.actividad_id=t2.id "
			+ " WHERE  t2.fecha_ingreso >= :initialDate and t2.fecha_ingreso <= :finalDate and t1.variable_id= :variableId and t2.abreviacion_rb= :stage  ORDER BY fecha_ingreso ASC", nativeQuery = true)
	public List<ActivityConsumption> getActivitiesByStage(
			@Param("stage") String stage,
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate,
			@Param("variableId") Long variableId);

}
