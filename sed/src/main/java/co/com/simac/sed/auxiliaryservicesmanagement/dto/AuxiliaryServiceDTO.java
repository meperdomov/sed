package co.com.simac.sed.auxiliaryservicesmanagement.dto;

import javax.validation.constraints.NotNull;

import co.com.simac.sed.kpiindicatormanagement.dto.VariableDTO;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class AuxiliaryServiceDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	private String name;
	@NotNull(message = "El campo variable no puede ser vacio")
	private VariableDTO variableDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VariableDTO getVariableDTO() {
		return variableDTO;
	}

	public void setVariableDTO(VariableDTO variableDTO) {
		this.variableDTO = variableDTO;
	}

}
