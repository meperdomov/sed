package co.com.simac.sed.kpiindicatormanagement.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.mvc.dto.DTOIdentidad;
/**
 * 
 * @author Juan Camilo
 *
 */
public class KPIIndicatorDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	@NotBlank(message = "El campo nombre no puede ser vacio")
	private String name;

	@NotNull(message = "El campo activo no puede ser vacio")
	private boolean active;

	private EquipmentDTO equipmentDTO;

	private MaterialDTO materialDTO;

	private RouteDTO routeDTO;

	private String stage;

	@NotNull(message = "El campo unidad de medida  no puede ser vacio")
	private UnitMeasureDTO unitMeasureDTO;

	@NotNull(message = "El campo variable no puede ser vacio")
	private VariableDTO variableDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public EquipmentDTO getEquipmentDTO() {
		return equipmentDTO;
	}

	public void setEquipmentDTO(EquipmentDTO equipmentDTO) {
		this.equipmentDTO = equipmentDTO;
	}

	public MaterialDTO getMaterialDTO() {
		return materialDTO;
	}

	public void setMaterialDTO(MaterialDTO materialDTO) {
		this.materialDTO = materialDTO;
	}

	public RouteDTO getRouteDTO() {
		return routeDTO;
	}

	public void setRouteDTO(RouteDTO routeDTO) {
		this.routeDTO = routeDTO;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public UnitMeasureDTO getUnitMeasureDTO() {
		return unitMeasureDTO;
	}

	public void setUnitMeasureDTO(UnitMeasureDTO unitMeasureDTO) {
		this.unitMeasureDTO = unitMeasureDTO;
	}

	public VariableDTO getVariableDTO() {
		return variableDTO;
	}

	public void setVariableDTO(VariableDTO variableDTO) {
		this.variableDTO = variableDTO;
	}

}
