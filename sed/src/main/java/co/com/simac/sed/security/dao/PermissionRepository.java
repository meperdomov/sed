package co.com.simac.sed.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import co.com.simac.sed.security.model.Permission;

/**
 * 
 * @author Juan Camilo
 *
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
}
