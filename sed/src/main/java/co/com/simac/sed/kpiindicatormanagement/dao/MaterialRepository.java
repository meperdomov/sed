package co.com.simac.sed.kpiindicatormanagement.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.cube.model.Material;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

	@Query("select m from Material m where m.name like %?1% OR m.code LIKE %?1%")
	List<Material> filterByQuery(String query, Pageable pageable);

}
