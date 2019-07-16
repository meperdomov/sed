package co.com.simac.sed.report.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.systemalarms.model.AlarmHistory;

@Repository
public interface AlarmHistoryRepository
		extends JpaRepository<AlarmHistory, Long>, JpaSpecificationExecutor<AlarmHistory> {

	@Query(value = "FROM AlarmHistory c WHERE c.kpiIndicatorAlarm.id = :kpiAlarmId and c.status=true")
	public List<AlarmHistory> getAlarmHistoryByKPIAlarmId(@Param("kpiAlarmId") Long kpiAlarmId);

}
