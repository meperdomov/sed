package co.com.simac.sed.report.service;

import java.util.Date;

import co.com.simac.sed.systemalarms.dto.AlarmHistoryDTO;
import co.com.simac.sed.util.PageDTO;


public interface AlarmHistoryService {
	
	public PageDTO<AlarmHistoryDTO> getAlarmHistoryPageDTO(int page, int size, Long idFailureCause, Long idKpiAlarm,
			Date initialDate, Date finalDate, int status);

}
