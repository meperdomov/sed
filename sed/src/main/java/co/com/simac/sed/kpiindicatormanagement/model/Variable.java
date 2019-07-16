package co.com.simac.sed.kpiindicatormanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.kpiindicatormanagement.dto.UnitMeasureDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.VariableDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;
import co.com.simac.sed.util.Utilities;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "variable")
public class Variable extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "unidad_medida_variable", joinColumns = {
			@JoinColumn(name = "variable_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "unidad_id", nullable = false, updatable = false) })
	private List<UnitMeasure> unitMeasures;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "variable", cascade = CascadeType.ALL)
	private List<KPIIndicator> kpiIndicators;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UnitMeasure> getUnitMeasures() {
		return unitMeasures;
	}

	public void setUnitMeasures(List<UnitMeasure> unitMeasures) {
		this.unitMeasures = unitMeasures;
	}

	public List<KPIIndicator> getKpiIndicators() {
		return kpiIndicators;
	}

	public void setKpiIndicators(List<KPIIndicator> kpiIndicators) {
		this.kpiIndicators = kpiIndicators;
	}

	@Override
	public VariableDTO getDTO(String... camposIgnorados) {
		VariableDTO dto = new VariableDTO();
		BeanUtils.copyProperties(this, dto);
		if (this.getUnitMeasures() != null && !this.getUnitMeasures().isEmpty()) {
			List<UnitMeasureDTO> unitMeasuresDTO = Utilities.toDTOList(this.getUnitMeasures(), UnitMeasureDTO.class);
			dto.setUnitMeasuresDTO(unitMeasuresDTO);
		}
		return dto;
	}

}
