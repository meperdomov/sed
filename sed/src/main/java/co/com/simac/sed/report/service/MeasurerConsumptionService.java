package co.com.simac.sed.report.service;

import java.util.Date;

import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface MeasurerConsumptionService {
	
	public PageDTO<Object> getMeasurerConsumptionPage(int page, int size, long idVariable,
			long idEquipment, long idMeasurer, long idUnitMeasure, Date initialDate, Date finalDate);
}
