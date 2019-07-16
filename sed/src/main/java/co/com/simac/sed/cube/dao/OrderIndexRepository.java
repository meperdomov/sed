package co.com.simac.sed.cube.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.report.model.KPIHistory;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface OrderIndexRepository extends JpaRepository<OrderIndex, Long> {

	@Query(value = "SELECT * FROM orden_indice INNER JOIN orden_produccion ON orden_indice.orden_id = orden_produccion.id  WHERE  orden_produccion.id= :orderId and orden_indice.variable_id= :variableId", nativeQuery = true)
	public List<OrderIndex> getOrderByVariable(@Param("orderId") Long orderId, @Param("variableId") Long variableId);

	@Query(value = "SELECT * FROM orden_indice INNER JOIN orden_produccion ON orden_indice.orden_id = orden_produccion.id "
			+ "INNER JOIN ruta ON ruta.id=orden_produccion.ruta_id WHERE  orden_produccion.fecha_inicio >= :initialDate and orden_produccion.fecha_inicio <= :finalDate and orden_indice.variable_id= :variableId and orden_produccion.ruta_id = :routeId", nativeQuery = true)
	public List<OrderIndex> getOrderConsumptionByRange(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, @Param("variableId") Long variableId, @Param("routeId") Long routeId);

	@Query(value = "SELECT * FROM orden_indice INNER JOIN orden_produccion ON orden_indice.orden_id = orden_produccion.id "
			+ "INNER JOIN ruta ON ruta.id=orden_produccion.ruta_id"
			+ "INNER JOIN material ON ruta.material_id=material.id  "
			+ "WHERE  orden_produccion.fecha_inicio >= :initialDate and orden_produccion.fecha_inicio <= :finalDate and orden_indice.variable_id= :variableId and orden_produccion.ruta_id = ::routeId and ruta.material_id= :materialId", nativeQuery = true)
	public List<OrderIndex> getOrderConsumptionByRouteMaterial(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, @Param("variableId") Long variableId, @Param("routeId") Long routeId,
			@Param("materialId") Long materialId);

	// Indicadores de equipo
	@Query(value = "SELECT * FROM orden_indice INNER JOIN equipamiento ON orden_indice.equipo_id = equipamiento.id "
			+ "INNER JOIN orden_produccion ON orden_produccion.id=orden_indice.orden_id WHERE  orden_produccion.fecha_inicio >= :initialDate and orden_produccion.fecha_inicio <= :finalDate and orden_indice.variable_id= :variableId and orden_indice.equipo_id = :equipmentId and orden_produccion.id= :orderId", nativeQuery = true)
	public List<OrderIndex> getEquipmentIndexByRange(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, @Param("variableId") Long variableId,
			@Param("equipmentId") Long equipmentId, @Param("orderId") Long orderId);

	@Query(value = "SELECT * FROM orden_indice INNER JOIN equipamiento ON orden_indice.equipo_id = equipamiento.id "
			+ "INNER JOIN orden_produccion ON orden_produccion.id=orden_indice.orden_id WHERE  orden_produccion.fecha_inicio >= :initialDate and orden_produccion.fecha_inicio <= :finalDate and orden_indice.variable_id= :variableId and orden_indice.equipo_id = :equipmentId and orden_indice.material_id= :materialId", nativeQuery = true)
	public List<OrderIndex> getEquipmentIndexByMaterial(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, @Param("variableId") Long variableId,
			@Param("equipmentId") Long equipmentId, @Param("materialId") Long materialId);

	@Query(value = "SELECT * FROM orden_indice INNER JOIN equipamiento ON orden_indice.equipo_id = equipamiento.id "
			+ "INNER JOIN orden_produccion ON orden_produccion.id=orden_indice.orden_id WHERE  orden_produccion.fecha_inicio >= :initialDate and orden_produccion.fecha_inicio <= :finalDate and orden_indice.variable_id= :variableId and orden_indice.equipo_id = :equipmentId", nativeQuery = true)
	public List<OrderIndex> getEquipmentIndexByRange(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, @Param("variableId") Long variableId,
			@Param("equipmentId") Long equipmentId);

	// Indicadores de etapa
	@Query(value = "SELECT * FROM orden_indice "
			+ "INNER JOIN orden_produccion ON orden_produccion.id=orden_indice.orden_id WHERE  orden_produccion.fecha_inicio >= :initialDate and orden_produccion.fecha_inicio <= :finalDate and orden_indice.variable_id= :variableId and orden_indice.etapa = :stage and orden_produccion.id= :orderId", nativeQuery = true)
	public List<OrderIndex> getStageIndexByRange(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, @Param("variableId") Long variableId, @Param("stage") String stage,
			@Param("orderId") Long orderId);

	@Query(value = "SELECT * FROM orden_indice  "
			+ "INNER JOIN orden_produccion ON orden_produccion.id=orden_indice.orden_id WHERE  orden_produccion.fecha_inicio >= :initialDate and orden_produccion.fecha_inicio <= :finalDate and orden_indice.variable_id= :variableId and orden_indice.etapa = :stage", nativeQuery = true)
	public List<OrderIndex> getStageIndexByRange(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, @Param("variableId") Long variableId, @Param("stage") String stage);

	@Query("SELECT oi FROM OrderIndex oi INNER JOIN oi.kpiHistories h" + " WHERE h IN (:kpiHistories)")
	Page<OrderIndex> findOrdersIndexByKpiHistory(@Param("kpiHistories") Collection<KPIHistory> kpiHistories,
			Pageable pageable);
	
	@Query(value = "SELECT * FROM orden_indice "
			+ "INNER JOIN orden_produccion ON orden_produccion.id=orden_indice.orden_id WHERE  orden_produccion.fecha_inicio > :initialDate", nativeQuery=true)
	List<OrderIndex> findOrdersIndexRecents(@Param("initialDate") Date initialDate);
}
