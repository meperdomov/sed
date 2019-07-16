package co.com.simac.sed.graphics.dto;

import java.util.List;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class ConsumptionProductionGraphDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -8146591353963146195L;

	

	private List<Object[]> serieConsumption;
	
	private List<Object[]> serieProduction;
	
	public List<Object[]> getSerieConsumption() {
		return serieConsumption;
	}
	public void setSerieConsumption(List<Object[]> serieConsumption) {
		this.serieConsumption = serieConsumption;
	}
	public List<Object[]> getSerieProduction() {
		return serieProduction;
	}
	public void setSerieProduction(List<Object[]> serieProduction) {
		this.serieProduction = serieProduction;
	}

	

}
