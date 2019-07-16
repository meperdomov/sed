package co.com.simac.sed.cube.dto;

import java.util.Date;

import co.com.simac.sed.kpiindicatormanagement.dto.UnitMeasureDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.VariableDTO;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class MeasurerConsumptionDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	private Date startDate;

	private Date endDate;

	private float consumption;

	private VariableDTO variableDTO;

	private UnitMeasureDTO unitMeasureDTO;

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

	public Float getConsumption() {
		return consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public VariableDTO getVariableDTO() {
		return variableDTO;
	}

	public void setVariableDTO(VariableDTO variableDTO) {
		this.variableDTO = variableDTO;
	}

	public UnitMeasureDTO getUnitMeasureDTO() {
		return unitMeasureDTO;
	}

	public void setUnitMeasureDTO(UnitMeasureDTO unitMeasureDTO) {
		this.unitMeasureDTO = unitMeasureDTO;
	}

}