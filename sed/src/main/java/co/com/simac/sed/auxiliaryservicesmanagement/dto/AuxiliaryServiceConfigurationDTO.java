package co.com.simac.sed.auxiliaryservicesmanagement.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import co.com.simac.sed.kpiindicatormanagement.dto.EquipmentDTO;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class AuxiliaryServiceConfigurationDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;
	@NotNull(message = "El campo porcentaje no puede ser vacio")
	@Max(100)
	private Float percent;

	@NotNull(message = "El campo equipamiento no puede ser vacio")
	private EquipmentDTO equipmentDTO;

	@NotNull(message = "El campo servicio auxiliar no puede ser vacio")
	private AuxiliaryServiceDTO auxiliaryServiceDTO;
	
	private boolean provideService;

	public EquipmentDTO getEquipmentDTO() {
		return equipmentDTO;
	}

	public void setEquipmentDTO(EquipmentDTO equipmentDTO) {
		this.equipmentDTO = equipmentDTO;
	}

	public AuxiliaryServiceDTO getAuxiliaryServiceDTO() {
		return auxiliaryServiceDTO;
	}

	public void setAuxiliaryServiceDTO(AuxiliaryServiceDTO auxiliaryServiceDTO) {
		this.auxiliaryServiceDTO = auxiliaryServiceDTO;
	}

	public Float getPercent() {
		return percent;
	}

	public void setPercent(Float percent) {
		this.percent = percent;
	}

	public boolean isProvideService() {
		return provideService;
	}

	public void setProvideService(boolean provideService) {
		this.provideService = provideService;
	}

}
