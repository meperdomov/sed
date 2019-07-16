package co.com.simac.sed.graphics.dto;

import java.util.List;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class CorrelationGraphDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -8146591353963146195L;

	private Double slope;

	private Double intercept;

	private Double correlation;

	private List<Object[]> series;

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	public Double getIntercept() {
		return intercept;
	}

	public void setIntercept(Double intercept) {
		this.intercept = intercept;
	}

	public Double getCorrelation() {
		return correlation;
	}

	public void setCorrelation(Double correlation) {
		this.correlation = correlation;
	}

	public List<Object[]> getSeries() {
		return series;
	}

	public void setSeries(List<Object[]> series) {
		this.series = series;
	}

}
