package co.com.simac.sed.kpiindicatormanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface KPIIndicatorRepository extends
		JpaRepository<KPIIndicator, Long> {

	public List<KPIIndicator> findByActiveTrue();

	@Query(value = "SELECT * FROM indicadorkpi as kpi INNER JOIN ruta AS r ON r.id = kpi.ruta_id  where  kpi.ruta_id is not null and kpi.equipo_id IS NULL", nativeQuery = true)
	public List<KPIIndicator> findByRouteNotNull();

	@Query(value = "SELECT * FROM indicadorkpi kpi where  kpi.ruta_id is not null and kpi.material_id is not null and kpi.equipo_id IS NULL", nativeQuery = true)
	public List<KPIIndicator> findByRouteAndMaterialNotNull();

	@Query(value = "SELECT * FROM indicadorkpi kpi where  kpi.variable_id= :variableId", nativeQuery = true)
	public List<KPIIndicator> findByVariableId(
			@Param("variableId") Long variableId);

	@Query(value = "SELECT * FROM indicadorkpi kpi where  kpi.equipo_id is not null and kpi.ruta_id IS NULL", nativeQuery = true)
	public List<KPIIndicator> findByEquipmentNotNull();

	@Query(value = "SELECT * FROM indicadorkpi kpi where  kpi.equipo_id is not null and kpi.material_id is not null and kpi.ruta_id IS NULL", nativeQuery = true)
	public List<KPIIndicator> findByEquipmentAndMaterialNotNull();

	@Query(value = "SELECT * FROM indicadorkpi kpi where  kpi.etapa is not null and kpi.ruta_id IS NULL and kpi.equipo_id IS NULL", nativeQuery = true)
	public List<KPIIndicator> findByStageNotNull();

	@Query(value = "SELECT count(*) FROM indicadorkpi kpi WHERE kpi.activo = 1", nativeQuery = true)
	public Integer getTotalActiveKPI();
	
	

}
