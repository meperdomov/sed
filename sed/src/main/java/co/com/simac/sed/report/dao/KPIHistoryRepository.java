package co.com.simac.sed.report.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.report.model.KPIHistory;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface KPIHistoryRepository extends JpaRepository<KPIHistory, Long>,
		JpaSpecificationExecutor<KPIHistory> {

	@Query(value = "FROM KPIHistory q1 where q1.startDate = "
			+ "(SELECT max(i.startDate) FROM KPIHistory i where i.kpiindicator = q1.kpiindicator"
			+ " GROUP BY i.kpiindicator) and q1.startDate >= :initialDate and q1.startDate <= :finalDate")
	public Page<KPIHistory> findRecentsKpiHistories(
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate, Pageable pageable);

	@Query(value = "SELECT * FROM historicokpi h "
			+ "inner join indicadorkpi i on i.id=h.indicador_kpi"
			+ " where h.fecha_inicio >= :initialDate and h.fecha_inicio <= :finalDate and h.indicador_kpi= :indicatorId order by h.fecha_inicio desc", nativeQuery = true)
	public List<KPIHistory> findKpiHistoriesByKPiIndicator(
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate,
			@Param("indicatorId") Long indicatorId);

	@Query(value = "SELECT * from historicokpi hk where hk.fecha_inicio=(select max(i.fecha_inicio) FROM historicokpi i where i.indicador_kpi = :indicatorId) and hk.indicador_kpi= :indicatorId order by hk.fecha_inicio desc", nativeQuery = true)
	public List<KPIHistory> findKpiHistoryRecentByIndicator(
			@Param("indicatorId") Long indicatorId);
}
