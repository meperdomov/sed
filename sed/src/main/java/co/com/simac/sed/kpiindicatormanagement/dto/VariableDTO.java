package co.com.simac.sed.kpiindicatormanagement.dto;

import java.util.List;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class VariableDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	private String name;

	private List<UnitMeasureDTO> unitMeasuresDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UnitMeasureDTO> getUnitMeasuresDTO() {
		return unitMeasuresDTO;
	}
	
	public void setUnitMeasuresDTO(List<UnitMeasureDTO> unitMeasuresDTO) {
		this.unitMeasuresDTO = unitMeasuresDTO;
	}
}
