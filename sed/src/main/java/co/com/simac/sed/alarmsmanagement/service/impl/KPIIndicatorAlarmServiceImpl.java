package co.com.simac.sed.alarmsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.alarmsmanagement.dao.KPIIndicatorAlarmRepository;
import co.com.simac.sed.alarmsmanagement.dto.KPIIndicatorAlarmDTO;
import co.com.simac.sed.alarmsmanagement.model.KPIIndicatorAlarm;
import co.com.simac.sed.alarmsmanagement.service.KPIIndicatorAlarmService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class KPIIndicatorAlarmServiceImpl implements KPIIndicatorAlarmService {

	@Autowired
	private KPIIndicatorAlarmRepository kpiIndicatorAlarmRepository;

	@Override
	public KPIIndicatorAlarmDTO getKPIIndicatorAlarmById(Long id) {
		KPIIndicatorAlarm kpiIndicatorAlarm = kpiIndicatorAlarmRepository.findOne(id);
		if (kpiIndicatorAlarm != null) {
			return kpiIndicatorAlarm.getDTO();
		}
		return null;
	}

	@Override
	public PageDTO<KPIIndicatorAlarmDTO> getKPIIndicatorAlarmPageDTO(Long alarmTypeId, Long kpiIndicatorId, int page,
			int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<KPIIndicatorAlarm> kpiIndicatorAlarmPage = kpiIndicatorAlarmRepository
				.getKPIIndicatorAlarmPage(alarmTypeId, kpiIndicatorId, pageable);
		return Utilities.toPageDTO(kpiIndicatorAlarmPage, KPIIndicatorAlarmDTO.class);
	}

	@Override
	public KPIIndicatorAlarm save(KPIIndicatorAlarmDTO kpiIndicatorAlarmDTO) {
		KPIIndicatorAlarm kpiIndicatorAlarm = new KPIIndicatorAlarm();
		Utilities.toEntity(kpiIndicatorAlarm, kpiIndicatorAlarmDTO);
		return kpiIndicatorAlarmRepository.save(kpiIndicatorAlarm);
	}

	@Override
	public List<KPIIndicatorAlarmDTO> getKPIIndicatorAlarmByIndicatorId(Long kpiIndicatorId) {
		List<KPIIndicatorAlarm> kpiIndicatorAlarms = kpiIndicatorAlarmRepository
				.getKPIIndicatorAlarmByIndicatorId(kpiIndicatorId);
		return Utilities.toDTOList(kpiIndicatorAlarms, KPIIndicatorAlarmDTO.class);
	}

	@Override
	public void delete(Long id) {
		kpiIndicatorAlarmRepository.delete(id);
	}
}