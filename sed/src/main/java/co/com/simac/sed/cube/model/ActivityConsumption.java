package co.com.simac.sed.cube.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "consumo_actividad")
public class ActivityConsumption extends EntidadIdentidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "variable_id", nullable = false)
	private Variable variable;

	@ManyToOne
	@JoinColumn(name = "unidad_medida_id", nullable = false)
	private UnitMeasure unitMeasure;

	@ManyToOne
	@JoinColumn(name = "actividad_id", unique = true, nullable = false)
	private Activity activity;

	@Column(name = "consumo", nullable = false)
	private Float consumption;

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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Float getConsumption() {
		return consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	@Override
	public DTOGenerico getDTO(String... camposIgnorados) {
		return null;
	}
}
