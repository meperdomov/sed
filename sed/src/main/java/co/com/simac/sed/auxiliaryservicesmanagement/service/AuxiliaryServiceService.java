package co.com.simac.sed.auxiliaryservicesmanagement.service;

import java.util.List;

import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceDTO;
import co.com.simac.sed.auxiliaryservicesmanagement.model.AuxiliaryService;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public interface AuxiliaryServiceService {

	public AuxiliaryServiceDTO findById(Long id);

	public List<AuxiliaryServiceDTO> findAll();

	public PageDTO<AuxiliaryServiceDTO> getAuxiliaryServicePageDTO(int page,
			int size);

	public AuxiliaryServiceDTO getAuxiliarServiceById(Long id);

	public AuxiliaryService save(AuxiliaryServiceDTO auxiliaryService);

	public void delete(Long id);
}