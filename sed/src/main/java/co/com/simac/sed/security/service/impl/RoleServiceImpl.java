package co.com.simac.sed.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.security.dao.PermissionRepository;
import co.com.simac.sed.security.dao.RoleRepository;
import co.com.simac.sed.security.dto.PermissionDTO;
import co.com.simac.sed.security.dto.RoleDTO;
import co.com.simac.sed.security.model.Permission;
import co.com.simac.sed.security.model.Role;
import co.com.simac.sed.security.service.RoleService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public PageDTO<RoleDTO> getRolePageDTO(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<Role> rolePage = roleRepository.findAll(pageable);
		return Utilities.toPageDTO(rolePage, RoleDTO.class);
	}

	@Override
	public RoleDTO getRoleByName(String name) {
		Role role = roleRepository.findByNameEquals(name);
		if (role != null) {
			return role.getDTO();
		}
		return null;
	}

	@Override
	public RoleDTO getRoleById(Long id) {
		Role role = roleRepository.findOne(id);
		return role.getDTO();
	}

	@Override
	public Role save(RoleDTO roleDTO) {
		Role role = new Role();
		Utilities.toEntity(role, roleDTO);
		role.setPermissions(populatePermissions(roleDTO));
		return roleRepository.save(role);
	}

	private List<Permission> populatePermissions(RoleDTO roleDTO) {
		List<Permission> permissions = new ArrayList<>();
		for (PermissionDTO permissionDTO : roleDTO.getPermissionsDTO()) {
			Permission permission = new Permission();
			Utilities.toEntity(permission, permissionDTO);
			permissions.add(permission);
		}
		return permissions;
	}

	@Override
	public void delete(Long id) {
		roleRepository.delete(id);
	}

	@Override
	public List<PermissionDTO> findAllPermissions() {
		ArrayList<Permission> permissions = (ArrayList<Permission>) permissionRepository.findAll();
		return Utilities.toDTOList(permissions, PermissionDTO.class);
	}

	@Override
	public List<RoleDTO> findAll() {
		ArrayList<Role> roles = (ArrayList<Role>) roleRepository.findAll();
		return Utilities.toDTOList(roles, RoleDTO.class);
	}

}
