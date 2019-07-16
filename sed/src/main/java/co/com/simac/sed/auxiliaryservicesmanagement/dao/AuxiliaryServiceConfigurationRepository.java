package co.com.simac.sed.auxiliaryservicesmanagement.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.auxiliaryservicesmanagement.model.AuxiliaryServiceConfiguration;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface AuxiliaryServiceConfigurationRepository extends JpaRepository<AuxiliaryServiceConfiguration, Long> {

	@Query(value = "SELECT sum(porcentaje)  FROM configuracion_servicio_auxiliar WHERE servicio_auxiliar_id = :auxiliaryServiceId and presta_servicio = 0", nativeQuery = true)
	public Float getTotalPercentNoProvideEquipments(@Param("auxiliaryServiceId") Long auxiliaryService);

	@Query(value = "SELECT sum(porcentaje)  FROM configuracion_servicio_auxiliar WHERE servicio_auxiliar_id = :auxiliaryServiceId and presta_servicio = 1", nativeQuery = true)
	public Float getTotalPercentProvideEquipments(@Param("auxiliaryServiceId") Long auxiliaryServiceId);

	@Query(value = "SELECT c FROM AuxiliaryServiceConfiguration c WHERE c.auxiliaryService.id = :auxiliaryServiceId")
	public Page<AuxiliaryServiceConfiguration> getAuxiliaryServiceConfigByAuxiliaryServiceId(
			@Param("auxiliaryServiceId") Long auxiliaryServiceId, Pageable pageable);

	@Query(value = "SELECT count(*) FROM configuracion_servicio_auxiliar WHERE servicio_auxiliar_id = :auxiliaryServiceId and equipo_id= :equipmentId and presta_servicio = 0", nativeQuery = true)
	public Integer getCountOfNoProvideEquipments(@Param("auxiliaryServiceId") Long auxiliaryServiceId,
			@Param("equipmentId") Long equipmentId);

	@Query(value = "SELECT count(*) FROM configuracion_servicio_auxiliar WHERE servicio_auxiliar_id = :auxiliaryServiceId and equipo_id= :equipmentId and presta_servicio = 1", nativeQuery = true)
	public Integer getCountOfProvideEquipments(@Param("auxiliaryServiceId") Long auxiliaryServiceId,
			@Param("equipmentId") Long equipmentId);

	@Query(value = "SELECT * FROM configuracion_servicio_auxiliar WHERE  presta_servicio = 1", nativeQuery = true)
	public List<AuxiliaryServiceConfiguration> getAuxiliaryServiceConfigByProvideEquipments();

}
