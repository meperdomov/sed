package co.com.simac.sed.general.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.general.dao.ParameterConfigurationRepository;
import co.com.simac.sed.general.dto.ParameterConfigurationDTO;
import co.com.simac.sed.general.model.ParameterConfiguration;
import co.com.simac.sed.general.service.ParameterConfigurationService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class ParameterConfigurationServiceImpl implements ParameterConfigurationService {

	@Autowired
	private ParameterConfigurationRepository parameterConfigurationRepository;

	@Override
	public PageDTO<ParameterConfigurationDTO> getParameterConfigurationPageDTO(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<ParameterConfiguration> configurationParametersPage = parameterConfigurationRepository.findAll(pageable);
		return Utilities.toPageDTO(configurationParametersPage, ParameterConfigurationDTO.class);
	}

	@Override
	public ParameterConfigurationDTO getParameterConfigurationById(Long id) {
		ParameterConfiguration parameterConfiguration = parameterConfigurationRepository.findOne(id);
		if (parameterConfiguration != null) {
			return parameterConfiguration.getDTO();
		}
		return null;
	}

	@Override
	public ParameterConfiguration save(ParameterConfigurationDTO configurationParametersDTO) {
		ParameterConfiguration configurationParameters = new ParameterConfiguration();
		Utilities.toEntity(configurationParameters, configurationParametersDTO);
		return parameterConfigurationRepository.save(configurationParameters);
	}

	@Override
	public void delete(Long id) {
		parameterConfigurationRepository.delete(id);
	}

	@Override
	public ParameterConfiguration getParameterConfigurationByKey(String key) {
		return parameterConfigurationRepository.findByKey(key);
	}

}