package co.com.simac.sed.security.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.mvc.modelo.EntidadIdentidad;
import co.com.simac.sed.security.dto.PermissionDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "permiso")
public class Permission extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "codigo", nullable = false, unique = true)
	private String code;

	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
	private List<Role> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public PermissionDTO getDTO(String... camposIgnorados) {
		PermissionDTO dto = new PermissionDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
