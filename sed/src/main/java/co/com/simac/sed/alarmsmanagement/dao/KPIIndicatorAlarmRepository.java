package co.com.simac.sed.alarmsmanagement.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.alarmsmanagement.model.KPIIndicatorAlarm;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface KPIIndicatorAlarmRepository extends JpaRepository<KPIIndicatorAlarm, Long> {

	@Query(value = "SELECT c FROM KPIIndicatorAlarm c WHERE c.alarmType.id = :alarmTypeId AND c.kpiIndicator.id = :kpiIndicatorId")
	public Page<KPIIndicatorAlarm> getKPIIndicatorAlarmPage(@Param("alarmTypeId") Long alarmTypeId,
			@Param("kpiIndicatorId") Long kpiIndicatorId, Pageable pageable);

	@Query(value = "SELECT c FROM KPIIndicatorAlarm c WHERE c.kpiIndicator.id = :kpiIndicatorId")
	public List<KPIIndicatorAlarm> getKPIIndicatorAlarmByIndicatorId(@Param("kpiIndicatorId") Long kpiIndicatorId);
	
	@Query(value = "SELECT c FROM KPIIndicatorAlarm c WHERE c.kpiIndicator.id = :kpiIndicatorId and c.alarmType.name='Correlación'")
	public List<KPIIndicatorAlarm> getKPIAlarmByKPIandCorrelacion(@Param("kpiIndicatorId") Long kpiIndicatorId);

}