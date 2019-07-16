package co.com.simac.sed.graphics.service;

import java.util.Date;
import java.util.List;

import co.com.simac.sed.report.dto.KPIHistoryDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface TrendGraphKPIIndicatorService {

	public KPIHistoryDTO getKPIHistoryById(Long id);

	public List<KPIHistoryDTO> getAllKPIHistoryByRange(Long idKpiIndicator,Long idVariable, Date initialDate, Date finalDate);

	public List<Object[]> getTrendGraph(Long idKpiIndicator,Long idVariable,Long idUnitMeasure, Date initialDate, Date finalDate);

}
