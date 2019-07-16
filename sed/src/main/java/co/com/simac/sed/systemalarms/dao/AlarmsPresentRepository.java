package co.com.simac.sed.systemalarms.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.systemalarms.model.AlarmHistory;

@Repository
public interface AlarmsPresentRepository extends JpaRepository<AlarmHistory, Long> {

	@Query(value = "FROM AlarmHistory q1 where  q1.startDate >= :initialDate and q1.startDate <= :finalDate and q1.status=1")
	public Page<AlarmHistory> findAlarmsPresent(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, Pageable pageable);

}
