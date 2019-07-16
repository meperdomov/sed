package co.com.simac.sed.kpiindicatormanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.kpiindicatormanagement.dto.UnitMeasureDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "unidad_medida")
public class UnitMeasure extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "factor_conversion", nullable = false)
	private float conversionFactor;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "unitMeasures")
	private List<Variable> variables;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMeasure", cascade = CascadeType.ALL)
	private List<KPIIndicator> kpiIndicators;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(float conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public List<KPIIndicator> getKpiIndicators() {
		return kpiIndicators;
	}

	public void setKpiIndicators(List<KPIIndicator> kpiIndicators) {
		this.kpiIndicators = kpiIndicators;
	}

	@Override
	public UnitMeasureDTO getDTO(String... camposIgnorados) {
		UnitMeasureDTO dto = new UnitMeasureDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
