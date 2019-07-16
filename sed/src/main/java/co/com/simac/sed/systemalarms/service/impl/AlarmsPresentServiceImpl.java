package co.com.simac.sed.systemalarms.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.systemalarms.dao.AlarmsPresentRepository;
import co.com.simac.sed.systemalarms.dto.AlarmHistoryDTO;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.systemalarms.service.AlarmsPresentService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class AlarmsPresentServiceImpl implements AlarmsPresentService {

	@Autowired
	private AlarmsPresentRepository alarmsPresentRepository;

	@Override
	public PageDTO<AlarmHistoryDTO> getAlarmsPresentPageDTO(int page, int size, Date initialDate, Date finalDate) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AlarmHistory> alarmsPresentPage = alarmsPresentRepository.findAlarmsPresent(initialDate, finalDate,
				pageable);
		return Utilities.toPageDTO(alarmsPresentPage, AlarmHistoryDTO.class);
	}

	@Override
	public AlarmHistory save(AlarmHistoryDTO alarmHistoryDTO) {
		AlarmHistory alarmHistory = new AlarmHistory();
		Utilities.toEntity(alarmHistory, alarmHistoryDTO);
		return alarmsPresentRepository.save(alarmHistory);
	}

}
