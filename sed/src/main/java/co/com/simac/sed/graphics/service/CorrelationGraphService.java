package co.com.simac.sed.graphics.service;

import co.com.simac.sed.graphics.dto.CorrelationGraphDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface CorrelationGraphService {

	public CorrelationGraphDTO getCorrelationGraph(Long idKpiHistory);

}
