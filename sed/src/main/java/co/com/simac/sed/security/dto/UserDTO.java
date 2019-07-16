package co.com.simac.sed.security.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class UserDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "El campo nombre no puede ser vacio")
	private String name;

	@NotBlank(message = "El campo email no puede ser vacio")
	@Email(message = "El campo email no posee un formato correcto")
	private String email;

	@NotBlank(message = "El campo nombre de usuario no puede ser vacio")
	private String userName;

	private String password;

	private RoleDTO roleDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

}
