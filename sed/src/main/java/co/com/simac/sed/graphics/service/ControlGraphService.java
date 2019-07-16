package co.com.simac.sed.graphics.service;

import java.util.Date;

import co.com.simac.sed.graphics.dto.ControlGraphDTO;


/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface ControlGraphService {

	public ControlGraphDTO getControlGraph(Long idKpiIndicator, Long idVariable, Long idUnitMeasure, Date initialDate,
			Date finalDate);

}
