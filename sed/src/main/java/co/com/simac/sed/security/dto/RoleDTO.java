package co.com.simac.sed.security.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class RoleDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "El campo nombre no puede ser vacio")
	private String name;

	private List<PermissionDTO> permissionsDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PermissionDTO> getPermissionsDTO() {
		return permissionsDTO;
	}

	public void setPermissionsDTO(List<PermissionDTO> permissionsDTO) {
		this.permissionsDTO = permissionsDTO;
	}

}
