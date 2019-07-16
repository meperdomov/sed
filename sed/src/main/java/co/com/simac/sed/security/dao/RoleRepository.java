package co.com.simac.sed.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.simac.sed.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findByNameEquals(String name);

}
