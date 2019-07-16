package co.com.simac.sed.kpiindicatormanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.cube.model.Equipment;
import co.com.simac.sed.cube.model.Material;
import co.com.simac.sed.cube.model.Route;
import co.com.simac.sed.kpiindicatormanagement.dto.KPIIndicatorDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "indicadorkpi")
public class KPIIndicator extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "activo", nullable = true)
	private boolean active;

	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipment equipment;

	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;

	@ManyToOne
	@JoinColumn(name = "ruta_id")
	private Route route;

	@Column(name = "etapa", nullable = true)
	private String stage;

	@ManyToOne
	@JoinColumn(name = "unidad_medida_id", nullable = false)
	private UnitMeasure unitMeasure;

	@ManyToOne
	@JoinColumn(name = "variable_id", nullable = false)
	private Variable variable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public UnitMeasure getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(UnitMeasure unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Override
	public KPIIndicatorDTO getDTO(String... camposIgnorados) {
		KPIIndicatorDTO dto = new KPIIndicatorDTO();
		BeanUtils.copyProperties(this, dto);
		if (this.getVariable() != null) {
			dto.setVariableDTO(this.getVariable().getDTO());
		}
		if (this.getEquipment() != null) {
			dto.setEquipmentDTO(this.getEquipment().getDTO());
		}
		if (this.getMaterial() != null) {
			dto.setMaterialDTO(this.getMaterial().getDTO());
		}
		if (this.getRoute() != null) {
			dto.setRouteDTO(this.getRoute().getDTO());
		}
		if (this.getUnitMeasure() != null) {
			dto.setUnitMeasureDTO(this.getUnitMeasure().getDTO());
		}
		return dto;
	}

}
