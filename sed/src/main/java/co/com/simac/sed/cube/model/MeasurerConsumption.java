package co.com.simac.sed.cube.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.cube.dto.MeasurerConsumptionDTO;
import co.com.simac.sed.kpiindicatormanagement.model.UnitMeasure;
import co.com.simac.sed.kpiindicatormanagement.model.Variable;
import co.com.simac.sed.mvc.dto.DTOGenerico;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "consumo_medidor")
public class MeasurerConsumption extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "fecha_inicio", nullable = true)
	private Date startDate;

	@Column(name = "fecha_fin", nullable = true)
	private Date endDate;

	@Column(name = "consumo", nullable = false)
	private float consumption;

	@ManyToOne
	@JoinColumn(name = "medidor_id", nullable = false)
	private Measurer measurer;

	@ManyToOne
	@JoinColumn(name = "variable_id", nullable = false)
	private Variable variable;

	@ManyToOne
	@JoinColumn(name = "unidad_medida_id", nullable = false)
	private UnitMeasure unitMeasure;

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

	public float getConsumption() {
		return consumption;
	}

	public void setConsumption(float consumption) {
		this.consumption = consumption;
	}

	public Measurer getMeasurer() {
		return measurer;
	}

	public void setMeasurer(Measurer measurer) {
		this.measurer = measurer;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public UnitMeasure getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(UnitMeasure unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	@Override
	public DTOGenerico getDTO(String... camposIgnorados) {
		MeasurerConsumptionDTO dto = new MeasurerConsumptionDTO();
		if (this.getVariable() != null) {
			dto.setVariableDTO(this.getVariable().getDTO());
		}
		if (this.getUnitMeasure() != null) {
			dto.setUnitMeasureDTO(this.getUnitMeasure().getDTO());
		}
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
