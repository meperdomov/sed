package co.com.simac.sed.causeFailureManagement.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

/**
 * 
 * @author Juan Camilo
 *
 */
public class FailureCategoryDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1235532231524866451L;

	@NotBlank(message = "El campo nombre no puede ser vacio")
	private String name;

	private List<FailureCauseDTO> failureCausesDTO;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<FailureCauseDTO> getFailureCausesDTO() {
		return failureCausesDTO;
	}
	
	public void setFailureCausesDTO(List<FailureCauseDTO> failureCausesDTO) {
		this.failureCausesDTO = failureCausesDTO;
	}
	
}
