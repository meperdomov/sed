package co.com.simac.sed.kpiindicatormanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.model.Equipment;
import co.com.simac.sed.kpiindicatormanagement.dao.EquipmentRepository;
import co.com.simac.sed.kpiindicatormanagement.dto.EquipmentDTO;
import co.com.simac.sed.kpiindicatormanagement.service.EquipmentService;
import co.com.simac.sed.util.Utilities;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	EquipmentRepository equipmentRepository;

	@Override
	public EquipmentDTO findById(Long id) {
		Equipment equipment = equipmentRepository.findOne(id);
		return equipment.getDTO();
	}

	@Override
	public List<EquipmentDTO> findAllParents() {
		List<Equipment> equipments = equipmentRepository.findAllParents();
		return Utilities.toDTOList(equipments, EquipmentDTO.class);
	}

	public List<EquipmentDTO> findChildrens(Equipment parent) {
		List<Equipment> childrens = equipmentRepository.findByParent(parent);
		return Utilities.toDTOList(childrens, EquipmentDTO.class);
	}

}
