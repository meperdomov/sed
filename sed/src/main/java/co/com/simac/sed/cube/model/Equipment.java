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

import co.com.simac.sed.kpiindicatormanagement.dto.EquipmentDTO;
import co.com.simac.sed.mvc.modelo.EntidadCubo;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "equipamiento")
public class Equipment extends EntidadCubo {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "padre_id")
	private Equipment parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Equipment> childrens;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment", cascade = CascadeType.ALL)
	private List<Measurer> measurers;

	@Column(name = "consume_por_actividad", nullable = false)
	private boolean consumedByActivity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Equipment getParent() {
		return parent;
	}

	public void setParent(Equipment parent) {
		this.parent = parent;
	}

	public List<Equipment> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Equipment> childrens) {
		this.childrens = childrens;
	}

	public List<Measurer> getMeasurers() {
		return measurers;
	}

	public void setMeasurers(List<Measurer> measurers) {
		this.measurers = measurers;
	}

	public boolean isConsumedByActivity() {
		return consumedByActivity;
	}

	public void setConsumedByActivity(boolean consumedByActivity) {
		this.consumedByActivity = consumedByActivity;
	}

	@Override
	public EquipmentDTO getDTO(String... camposIgnorados) {
		EquipmentDTO dto = new EquipmentDTO();
		BeanUtils.copyProperties(this, dto);
		if (getChildrens() != null) {
			dto.setHasChildren(!getChildrens().isEmpty());
		}
		return dto;
	}
}
