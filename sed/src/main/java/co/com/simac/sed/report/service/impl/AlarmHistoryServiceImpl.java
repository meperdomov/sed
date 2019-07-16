package co.com.simac.sed.report.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.report.dao.AlarmHistoryRepository;
import co.com.simac.sed.report.dao.AlarmHistorySpecifications;
import co.com.simac.sed.report.service.AlarmHistoryService;
import co.com.simac.sed.systemalarms.dto.AlarmHistoryDTO;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class AlarmHistoryServiceImpl implements AlarmHistoryService{

	@Autowired
	private AlarmHistoryRepository alarmHistoryRepository;
	
	@Override
	public PageDTO<AlarmHistoryDTO> getAlarmHistoryPageDTO(int page, int size, Long idFailureCause, Long idKpiAlarm,
			Date initialDate, Date finalDate, int status) {
		
		Pageable pageable = new PageRequest(page - 1, size);
		ArrayList<AlarmHistory> alarmHistoryList = (ArrayList<AlarmHistory>) alarmHistoryRepository
				.findAll(AlarmHistorySpecifications.filter(idFailureCause,
						idKpiAlarm, initialDate, finalDate, status));

		Page<AlarmHistory> KPIHistoryPage = new PageImpl<AlarmHistory>(
				alarmHistoryList, pageable, alarmHistoryList.size());
		return Utilities.toPageDTO(KPIHistoryPage, AlarmHistoryDTO.class);
	}

}
