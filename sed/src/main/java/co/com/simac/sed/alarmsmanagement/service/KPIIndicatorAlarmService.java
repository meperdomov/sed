package co.com.simac.sed.alarmsmanagement.service;

import java.util.List;

import co.com.simac.sed.alarmsmanagement.dto.KPIIndicatorAlarmDTO;
import co.com.simac.sed.alarmsmanagement.model.KPIIndicatorAlarm;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface KPIIndicatorAlarmService {

	public PageDTO<KPIIndicatorAlarmDTO> getKPIIndicatorAlarmPageDTO(Long alarmTypeId, Long kpiIndicatorId, int page,
			int size);

	public KPIIndicatorAlarmDTO getKPIIndicatorAlarmById(Long id);

	public KPIIndicatorAlarm save(KPIIndicatorAlarmDTO kpiIndicatorAlarmDTO);

	public List<KPIIndicatorAlarmDTO> getKPIIndicatorAlarmByIndicatorId(Long kpiIndicatorId);
	
	public void delete(Long id);

}
