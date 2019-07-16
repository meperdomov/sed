package co.com.simac.sed.cube.service;

import java.util.List;

import co.com.simac.sed.cube.dto.MeasurerDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public interface MeasurerService {

	public List<MeasurerDTO> findByEquipmentId(Long id);

}
