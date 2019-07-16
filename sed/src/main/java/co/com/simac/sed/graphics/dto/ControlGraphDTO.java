package co.com.simac.sed.graphics.dto;

import java.util.List;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class ControlGraphDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -8146591353963146195L;

	private Double upperLimit;

	private Double lowerLimit;

	private List<Object[]> series;

	public List<Object[]> getSeries() {
		return series;
	}

	public void setSeries(List<Object[]> series) {
		this.series = series;
	}

	public Double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

}
