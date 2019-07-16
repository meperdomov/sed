package co.com.simac.sed.report.service;

import java.util.Date;

import co.com.simac.sed.cube.dto.OrderIndexDTO;
import co.com.simac.sed.report.dto.KPIHistoryDTO;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface KPIHistoryService {

	public KPIHistoryDTO findById(Long id);

	public PageDTO<KPIHistoryDTO> getKPIHistoryPageDTO(int page, int size, Long idKpiIndicator, Long idUnitMeasure,
			Date initialDate, Date finalDate);

	public PageDTO<KPIHistoryDTO> getRecentsKPIHistoryPageDTO(int page, int size, Date initialDate, Date finalDate);

	public PageDTO<OrderIndexDTO> getOrdersIndexByKPIHistoryPageDTO(int page, int size, long idKpiHistory);
}
