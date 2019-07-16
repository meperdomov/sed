package co.com.simac.sed.systemalarms.service;

import java.util.Date;

import co.com.simac.sed.systemalarms.dto.AlarmHistoryDTO;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface AlarmsPresentService {

	PageDTO<AlarmHistoryDTO> getAlarmsPresentPageDTO(int page, int size, Date initialDate, Date finalDate);

	AlarmHistory save(AlarmHistoryDTO alarmHistoryDTO);

}
