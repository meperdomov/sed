package co.com.simac.sed.graphics.service;

import co.com.simac.sed.graphics.dto.ConsumptionProductionGraphDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface ConsumptionProductionGraphService {

	public ConsumptionProductionGraphDTO getConsumptionProductionGraph(
			Long idKpiHistory);

}
