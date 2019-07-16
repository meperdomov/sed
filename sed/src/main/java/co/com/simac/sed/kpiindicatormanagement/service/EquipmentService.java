package co.com.simac.sed.kpiindicatormanagement.service;

import java.util.List;

import co.com.simac.sed.cube.model.Equipment;
import co.com.simac.sed.kpiindicatormanagement.dto.EquipmentDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public interface EquipmentService {

	public EquipmentDTO findById(Long id);

	public List<EquipmentDTO> findAllParents();

	public List<EquipmentDTO> findChildrens(Equipment parent);

}
