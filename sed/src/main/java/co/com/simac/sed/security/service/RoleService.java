package co.com.simac.sed.security.service;

import java.util.List;

import co.com.simac.sed.security.dto.PermissionDTO;
import co.com.simac.sed.security.dto.RoleDTO;
import co.com.simac.sed.security.model.Role;
import co.com.simac.sed.util.PageDTO;

public interface RoleService {
	
    public List<RoleDTO> findAll();   
    
    public PageDTO<RoleDTO> getRolePageDTO(int page, int size);

	public RoleDTO getRoleById(Long id);

	public Role save(RoleDTO role);

	public void delete(Long id);
	
	public List<PermissionDTO> findAllPermissions();

	RoleDTO getRoleByName(String name);

}
