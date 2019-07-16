package co.com.simac.sed.kpiindicatormanagement.service;

import java.util.List;

import co.com.simac.sed.cube.model.Material;
import co.com.simac.sed.kpiindicatormanagement.dto.RouteDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public interface RouteService {

	public RouteDTO findById(Long id);

	public List<RouteDTO> findAll();

	List<RouteDTO> filterByQuery(String query);

	List<RouteDTO> findByMaterial(Material material);

}
