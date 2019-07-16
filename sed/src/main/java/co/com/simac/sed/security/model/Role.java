package co.com.simac.sed.security.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.mvc.modelo.EntidadIdentidad;
import co.com.simac.sed.security.dto.PermissionDTO;
import co.com.simac.sed.security.dto.RoleDTO;
import co.com.simac.sed.util.Utilities;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "rol")
public class Role extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false, unique = true)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private List<User> users;

	//Relacion roles y permisos
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "rol_permiso", joinColumns = {
			@JoinColumn(name = "rol_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "permiso_id", nullable = false, updatable = false) })
	private List<Permission> permissions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public RoleDTO getDTO(String... camposIgnorados) {
		RoleDTO dto = new RoleDTO();
		BeanUtils.copyProperties(this, dto);
		List<Permission> permissions = getPermissions();
		if (permissions != null && !permissions.isEmpty()) {
			List<PermissionDTO> permissionsDTO = Utilities.toDTOList(permissions, PermissionDTO.class);
			dto.setPermissionsDTO(permissionsDTO);
		}
		return dto;
	}
}
