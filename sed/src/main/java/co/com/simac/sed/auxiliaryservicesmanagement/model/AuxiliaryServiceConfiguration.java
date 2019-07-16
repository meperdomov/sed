package co.com.simac.sed.auxiliaryservicesmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceConfigurationDTO;
import co.com.simac.sed.cube.model.Equipment;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "configuracion_servicio_auxiliar")
public class AuxiliaryServiceConfiguration extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "porcentaje", nullable = false)
	private Float percent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipo_id")
	private Equipment equipment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio_auxiliar_id")
	private AuxiliaryService auxiliaryService;

	@Column(name = "presta_servicio", nullable = false)
	private boolean provideService;

	public Float getPercent() {
		return percent;
	}

	public void setPercent(Float percent) {
		this.percent = percent;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public AuxiliaryService getAuxiliaryService() {
		return auxiliaryService;
	}

	public void setAuxiliaryService(AuxiliaryService auxiliaryService) {
		this.auxiliaryService = auxiliaryService;
	}

	public boolean isProvideService() {
		return provideService;
	}

	public void setProvideService(boolean provideService) {
		this.provideService = provideService;
	}

	@Override
	public AuxiliaryServiceConfigurationDTO getDTO(String... camposIgnorados) {
		AuxiliaryServiceConfigurationDTO dto = new AuxiliaryServiceConfigurationDTO();
		BeanUtils.copyProperties(this, dto);

		if (this.getEquipment() != null) {
			dto.setEquipmentDTO(this.getEquipment().getDTO());
		}

		if (this.getAuxiliaryService() != null) {
			dto.setAuxiliaryServiceDTO(this.getAuxiliaryService().getDTO());
		}
		return dto;
	}

}
