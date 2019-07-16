package co.com.simac.sed.cube.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.kpiindicatormanagement.dto.RouteDTO;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.mvc.modelo.EntidadCubo;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "ruta")
public class Route extends EntidadCubo {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "route", cascade = CascadeType.ALL)
	private List<KPIIndicator> kpiIndicators;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<KPIIndicator> getKpiIndicators() {
		return kpiIndicators;
	}

	public void setKpiIndicators(List<KPIIndicator> kpiIndicators) {
		this.kpiIndicators = kpiIndicators;
	}

	@Override
	public RouteDTO getDTO(String... camposIgnorados) {
		RouteDTO dto = new RouteDTO();
		BeanUtils.copyProperties(this, dto);
		if (this.getMaterial() != null) {
			dto.setMaterialDTO(this.getMaterial().getDTO());
		}
		return dto;
	}
}
