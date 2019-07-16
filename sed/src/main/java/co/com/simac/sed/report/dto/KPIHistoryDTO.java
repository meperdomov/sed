package co.com.simac.sed.report.dto;

import java.util.Date;

import co.com.simac.sed.kpiindicatormanagement.dto.KPIIndicatorDTO;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class KPIHistoryDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	private Date startDate;

	private Date endDate;

	private double averageValue;

	private Double correlation;

	private Double deviation;

	private Double intercept;

	private Double slope;

	private boolean auxiliaryService;

	private boolean isUtil;

	private KPIIndicatorDTO kpiIndicatorDTO;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getAverageValue() {
		return averageValue;
	}

	public void setAverageValue(double averageValue) {
		this.averageValue = averageValue;
	}

	public Double getCorrelation() {
		return correlation;
	}

	public void setCorrelation(Double correlation) {
		this.correlation = correlation;
	}

	public Double getDeviation() {
		return deviation;
	}

	public void setDeviation(Double deviation) {
		this.deviation = deviation;
	}

	public Double getIntercept() {
		return intercept;
	}

	public void setIntercept(Double intercept) {
		this.intercept = intercept;
	}

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	public boolean isAuxiliaryService() {
		return auxiliaryService;
	}

	public void setAuxiliaryService(boolean auxiliaryService) {
		this.auxiliaryService = auxiliaryService;
	}

	public boolean getUtil() {
		return isUtil;
	}

	public void setUtil(boolean isUtil) {
		this.isUtil = isUtil;
	}

	public KPIIndicatorDTO getKpiIndicatorDTO() {
		return kpiIndicatorDTO;
	}

	public void setKpiIndicatorDTO(KPIIndicatorDTO kpiIndicatorDTO) {
		this.kpiIndicatorDTO = kpiIndicatorDTO;
	}

}