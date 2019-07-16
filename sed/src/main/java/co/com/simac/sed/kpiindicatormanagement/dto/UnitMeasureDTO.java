package co.com.simac.sed.kpiindicatormanagement.dto;

import java.util.List;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

/**	
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public class UnitMeasureDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String conversionFactor;

	private List<VariableDTO> variablesDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(String conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	public List<VariableDTO> getVariablesDTO() {
		return variablesDTO;
	}
	
	public void setVariablesDTO(List<VariableDTO> variablesDTO) {
		this.variablesDTO = variablesDTO;
	}
}
