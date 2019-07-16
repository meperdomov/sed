package co.com.simac.sed.auxiliaryservicesmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.auxiliaryservicesmanagement.dao.AuxiliaryServiceConfigurationRepository;
import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceConfigurationDTO;
import co.com.simac.sed.auxiliaryservicesmanagement.model.AuxiliaryServiceConfiguration;
import co.com.simac.sed.auxiliaryservicesmanagement.service.AuxiliaryServiceConfigurationService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class AuxiliaryServiceConfigurationServiceImpl implements AuxiliaryServiceConfigurationService {

	@Autowired
	private AuxiliaryServiceConfigurationRepository auxiliaryServiceConfigRepository;

	@Override
	public PageDTO<AuxiliaryServiceConfigurationDTO> getAuxiliaryServiceConfigPageDTO(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AuxiliaryServiceConfiguration> auxiliaryServicePage = auxiliaryServiceConfigRepository.findAll(pageable);
		return Utilities.toPageDTO(auxiliaryServicePage, AuxiliaryServiceConfigurationDTO.class);
	}

	@Override
	public AuxiliaryServiceConfigurationDTO getAuxiliarServiceConfigById(Long id) {
		AuxiliaryServiceConfiguration auxiliaryServiceConfig = auxiliaryServiceConfigRepository.findOne(id);
		return (auxiliaryServiceConfig != null) ? auxiliaryServiceConfig.getDTO() : null;
	}

	@Override
	public AuxiliaryServiceConfiguration save(AuxiliaryServiceConfigurationDTO auxiliaryServiceConfigDTO) {
		AuxiliaryServiceConfiguration auxiliaryServiceConfig = new AuxiliaryServiceConfiguration();
		Utilities.toEntity(auxiliaryServiceConfig, auxiliaryServiceConfigDTO);

		return auxiliaryServiceConfigRepository.save(auxiliaryServiceConfig);
	}

	@Override
	public void delete(Long id) {
		auxiliaryServiceConfigRepository.delete(id);
	}

	@Override
	public Float getTotalPercentNoProvideEquipments(Long auxiliaryServiceId, Long auxiliaryServiceConfigId) {
		Float sum = auxiliaryServiceConfigRepository.getTotalPercentNoProvideEquipments(auxiliaryServiceId);
		if (sum != null && sum != 0) {
			if (auxiliaryServiceConfigId != null) {
				AuxiliaryServiceConfiguration editObject = auxiliaryServiceConfigRepository
						.findOne(auxiliaryServiceConfigId);
				sum = sum - editObject.getPercent();
			}
			return sum;
		}
		return (float) 0.00;
	}

	@Override
	public Float getTotalPercentProvideEquipments(Long auxiliaryServiceId, Long auxiliaryServiceConfigId) {
		Float sum = auxiliaryServiceConfigRepository.getTotalPercentProvideEquipments(auxiliaryServiceId);
		if (sum != null && sum != 0) {
			if (auxiliaryServiceConfigId != null) {
				AuxiliaryServiceConfiguration editObject = auxiliaryServiceConfigRepository
						.findOne(auxiliaryServiceConfigId);
				sum = sum - editObject.getPercent();
			}
			return sum;
		}
		return (float) 0.00;
	}

	@Override
	public PageDTO<AuxiliaryServiceConfigurationDTO> getAuxiliaryServiceConfigByAuxiliaryServiceId(
			Long auxiliaryServiceId, int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AuxiliaryServiceConfiguration> auxiliaryServiceConfigPage = auxiliaryServiceConfigRepository
				.getAuxiliaryServiceConfigByAuxiliaryServiceId(auxiliaryServiceId, pageable);
		return Utilities.toPageDTO(auxiliaryServiceConfigPage, AuxiliaryServiceConfigurationDTO.class);
	}

	@Override
	public Integer getCountOfProvideEquipments(Long auxiliaryServiceId, Long equipmentId) {
		Integer count = auxiliaryServiceConfigRepository.getCountOfProvideEquipments(auxiliaryServiceId, equipmentId);
		if (count != null) {
			return count;
		}
		return 0;
	}

	@Override
	public Integer getCountOfNoProvideEquipments(Long auxiliaryServiceId, Long equipmentId) {
		Integer count = auxiliaryServiceConfigRepository.getCountOfNoProvideEquipments(auxiliaryServiceId, equipmentId);
		if (count != null) {
			return count;
		}
		return 0;
	}

}
