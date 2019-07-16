package co.com.simac.sed.cube.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.kpiindicatormanagement.dto.MaterialDTO;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.mvc.modelo.EntidadCubo;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "material")
public class Material extends EntidadCubo {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;
	
	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "codigo", nullable = false)
	private String code;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade = CascadeType.ALL)
	private List<KPIIndicator> kpiIndicators;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public List<KPIIndicator> getKpiIndicators() {
		return kpiIndicators;
	}

	public void setKpiIndicators(List<KPIIndicator> kpiIndicators) {
		this.kpiIndicators = kpiIndicators;
	}

	@Override
	public MaterialDTO getDTO(String... camposIgnorados) {
		MaterialDTO dto = new MaterialDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
