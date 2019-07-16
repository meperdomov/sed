package co.com.simac.sed.auxiliaryservicesmanagement.service;

import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceConfigurationDTO;
import co.com.simac.sed.auxiliaryservicesmanagement.model.AuxiliaryServiceConfiguration;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface AuxiliaryServiceConfigurationService {

	public PageDTO<AuxiliaryServiceConfigurationDTO> getAuxiliaryServiceConfigPageDTO(int page, int size);
		
	public AuxiliaryServiceConfigurationDTO getAuxiliarServiceConfigById(Long id);
	
	public AuxiliaryServiceConfiguration save(AuxiliaryServiceConfigurationDTO indicador);

	public void delete(Long id);

	public Float getTotalPercentNoProvideEquipments(Long auxiliaryServiceId,Long auxiliaryServiceConfigId);
	
	public PageDTO<AuxiliaryServiceConfigurationDTO> getAuxiliaryServiceConfigByAuxiliaryServiceId(Long auxiliaryServiceId,int page, int size);
	
	public Integer getCountOfProvideEquipments(Long auxiliaryServiceId,Long equipmentId);
	
	public Float getTotalPercentProvideEquipments(Long auxiliaryServiceId,Long auxiliaryServiceConfigId);

	Integer getCountOfNoProvideEquipments(Long auxiliaryServiceId, Long equipmentId);
}
