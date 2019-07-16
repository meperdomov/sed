package co.com.simac.sed.kpiindicatormanagement.dao;







import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.Material;
import co.com.simac.sed.cube.model.Route;


/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
	
	@Query("select r from Route r where r.name like %?1%")
	public List<Route> filterByQuery(String query, Pageable pageable);
	
	public List<Route> findByMaterial(Material material, Pageable pageable);
	
}
