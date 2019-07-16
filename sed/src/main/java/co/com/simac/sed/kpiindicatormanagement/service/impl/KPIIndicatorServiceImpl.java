package co.com.simac.sed.kpiindicatormanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.dao.ActivityRepository;
import co.com.simac.sed.kpiindicatormanagement.dao.KPIIndicatorRepository;
import co.com.simac.sed.kpiindicatormanagement.dto.KPIIndicatorDTO;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.kpiindicatormanagement.service.KPIIndicatorService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class KPIIndicatorServiceImpl implements KPIIndicatorService {

	@Autowired
	private KPIIndicatorRepository kpiIndicatorRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public PageDTO<KPIIndicatorDTO> getKpiIndicatorPageDTO(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<KPIIndicator> kpiIndicatorPage = kpiIndicatorRepository
				.findAll(pageable);
		return Utilities.toPageDTO(kpiIndicatorPage, KPIIndicatorDTO.class);
	}

	@Override
	public KPIIndicatorDTO getKpiIndicatorById(Long id) {
		KPIIndicator kpiIndicator = kpiIndicatorRepository.findOne(id);
		return kpiIndicator.getDTO();
	}

	@Override
	public KPIIndicator save(KPIIndicatorDTO kpiIndicatorDTO) {
		KPIIndicator kpiIndicator = new KPIIndicator();
		Utilities.toEntity(kpiIndicator, kpiIndicatorDTO);
		return kpiIndicatorRepository.save(kpiIndicator);
	}

	@Override
	public void delete(Long id) {
		kpiIndicatorRepository.delete(id);
	}

	@Override
	public List<KPIIndicatorDTO> findByActiveTrue() {
		ArrayList<KPIIndicator> indicators = (ArrayList<KPIIndicator>) kpiIndicatorRepository
				.findByActiveTrue();
		return Utilities.toDTOList(indicators, KPIIndicatorDTO.class);
	}

	@Override
	public List<KPIIndicatorDTO> findByVariableId(Long variableId) {
		ArrayList<KPIIndicator> indicators = (ArrayList<KPIIndicator>) kpiIndicatorRepository
				.findByVariableId(variableId);
		return Utilities.toDTOList(indicators, KPIIndicatorDTO.class);
	}

	@Override
	public List<String> findStageAll() {
		ArrayList<String> stages = (ArrayList<String>) activityRepository
				.findStageByActivity();
		return stages;
	}

}
