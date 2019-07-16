package co.com.simac.sed.kpiindicatormanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.model.Material;
import co.com.simac.sed.kpiindicatormanagement.dao.MaterialRepository;
import co.com.simac.sed.kpiindicatormanagement.dto.MaterialDTO;
import co.com.simac.sed.kpiindicatormanagement.service.MaterialService;
import co.com.simac.sed.util.Utilities;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	@Override
	public MaterialDTO findById(Long id) {
		Material material = materialRepository.findOne(id);
		return material.getDTO();
	}

	@Override
	public List<MaterialDTO> filterByQuery(String query) {
		PageRequest pageRequest = new PageRequest(0, 1000);
		List<Material> materials = materialRepository.filterByQuery(query, pageRequest);
		return Utilities.toDTOList(materials, MaterialDTO.class);
	}

}
