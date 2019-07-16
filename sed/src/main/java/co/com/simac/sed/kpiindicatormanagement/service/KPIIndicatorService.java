package co.com.simac.sed.kpiindicatormanagement.service;

import java.util.List;

import co.com.simac.sed.kpiindicatormanagement.dto.KPIIndicatorDTO;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface KPIIndicatorService {

	public PageDTO<KPIIndicatorDTO> getKpiIndicatorPageDTO(int page, int size);

	public KPIIndicatorDTO getKpiIndicatorById(Long id);
	
	public KPIIndicator save(KPIIndicatorDTO indicador);
	

	public void delete(Long id);
	
	public List<KPIIndicatorDTO> findByActiveTrue();
	
	

	public List<KPIIndicatorDTO> findByVariableId(Long id);
	
	public List<String> findStageAll();

}
