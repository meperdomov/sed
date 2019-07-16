package co.com.simac.sed.causeFailureManagement.dto;

import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

/**
 * 
 * @author Maria Perdomo SIMAC
 *
 */
public class FailureCauseDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1235532231524866451L;

	@NotBlank(message = "La descripción no puede ser vacia")
	private String description;

	private FailureCategoryDTO failureCategoryDTO;

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public FailureCategoryDTO getFailureCategoryDTO() {
		return failureCategoryDTO;
	}


	public void setFailureCategoryDTO(FailureCategoryDTO failureCategoryDTO) {
		this.failureCategoryDTO = failureCategoryDTO;
	}

	

}
