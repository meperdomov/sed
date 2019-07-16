package co.com.simac.sed.general.service;

import co.com.simac.sed.general.dto.ParameterConfigurationDTO;
import co.com.simac.sed.general.model.ParameterConfiguration;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface ParameterConfigurationService {

	public PageDTO<ParameterConfigurationDTO> getParameterConfigurationPageDTO(
			int page, int size);

	public ParameterConfigurationDTO getParameterConfigurationById(Long id);
	
	public ParameterConfiguration getParameterConfigurationByKey(String key);

	public ParameterConfiguration save(ParameterConfigurationDTO parameterConfigurationDTO);

	public void delete(Long id);

}
