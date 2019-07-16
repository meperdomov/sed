package co.com.simac.sed.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.mvc.modelo.EntidadIdentidad;
import co.com.simac.sed.security.dto.UserDTO;

@Entity
@Table(name = "usuario")
public class User extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "nombre_usuario", nullable = false, unique = true)
	private String userName;
	@Column(name = "password", nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "rol_id", nullable = false)
	private Role role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public UserDTO getDTO(String... camposIgnorados) {
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(this, dto, camposIgnorados);
		if (getRole() != null) {
			Role role = getRole();
			dto.setRoleDTO(role.getDTO());
		}
		return dto;
	}
}
