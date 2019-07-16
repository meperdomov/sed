package co.com.simac.sed.kpiindicatormanagement.dto;

import java.util.List;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class EquipmentDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;
	private String name;
	private EquipmentDTO equipmentDTO;
	private List<EquipmentDTO> equipmentsDTO;
	private boolean hasChildren;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EquipmentDTO getEquipmentDTO() {
		return equipmentDTO;
	}

	public void setEquipmentDTO(EquipmentDTO equipmentDTO) {
		this.equipmentDTO = equipmentDTO;
	}

	public List<EquipmentDTO> getEquipmentsDTO() {
		return equipmentsDTO;
	}

	public void setEquipmentsDTO(List<EquipmentDTO> equipmentsDTO) {
		this.equipmentsDTO = equipmentsDTO;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}
	
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
}
