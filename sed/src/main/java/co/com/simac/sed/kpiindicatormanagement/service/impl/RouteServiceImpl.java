package co.com.simac.sed.kpiindicatormanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.model.Material;
import co.com.simac.sed.cube.model.Route;
import co.com.simac.sed.kpiindicatormanagement.dao.RouteRepository;
import co.com.simac.sed.kpiindicatormanagement.dto.RouteDTO;
import co.com.simac.sed.kpiindicatormanagement.service.RouteService;
import co.com.simac.sed.util.Utilities;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteRepository routeRepository;

	@Override
	public RouteDTO findById(Long id) {
		Route route = routeRepository.findOne(id);
		return route.getDTO();
	}

	@Override
	public List<RouteDTO> findAll() {
		List<Route> routes = routeRepository.findAll();
		return Utilities.toDTOList(routes, RouteDTO.class);
	}

	@Override
	public List<RouteDTO> filterByQuery(String query) {
		PageRequest pageRequest = new PageRequest(0, 1000);
		List<Route> routes = routeRepository.filterByQuery(query, pageRequest);
		return Utilities.toDTOList(routes, RouteDTO.class);
	}

	@Override
	public List<RouteDTO> findByMaterial(Material material) {
		PageRequest pageRequest = new PageRequest(0, 1000);
		List<Route> routes = routeRepository.findByMaterial(material, pageRequest);
		return Utilities.toDTOList(routes, RouteDTO.class);
	}

}
