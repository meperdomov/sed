package co.com.simac.sed.cube.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.cube.dto.OrderIndexDTO;
import co.com.simac.sed.kpiindicatormanagement.model.UnitMeasure;
import co.com.simac.sed.kpiindicatormanagement.model.Variable;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;
import co.com.simac.sed.report.model.KPIHistory;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "orden_indice")
public class OrderIndex extends EntidadIdentidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "consumo_directo", nullable = false)
	private Float directConsumption;

	@Column(name = "consumo_indirecto", nullable = false)
	private Float indirectConsumption;

	@Column(name = "cantidad", nullable = false)
	private Float quantity;

	@Column(name = "indice_directo", nullable = false)
	private Float directIndex;

	@Column(name = "indice_indirecto", nullable = false)
	private Float indirectIndex;

	@ManyToOne
	@JoinColumn(name = "variable_id", nullable = false)
	private Variable variable;

	@ManyToOne
	@JoinColumn(name = "unidad_medida_id", nullable = false)
	private UnitMeasure unitMeasure;

	@ManyToOne
	@JoinColumn(name = "orden_id", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "equipo_id", nullable = true)
	private Equipment equipment;

	@ManyToOne
	@JoinColumn(name = "material_id", nullable = true)
	private Material material;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ordersIndex")
	private List<KPIHistory> kpiHistories;

	@Column(name = "etapa", nullable = true)
	private String stage;

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

	public Float getDirectConsumption() {
		return directConsumption;
	}

	public void setDirectConsumption(Float consumption) {
		this.directConsumption = consumption;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getDirectIndex() {
		return directIndex;
	}

	public void setDirectIndex(Float index) {
		this.directIndex = index;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public List<KPIHistory> getKpiHistories() {
		return kpiHistories;
	}

	public void setKpiHistories(List<KPIHistory> kpiHistories) {
		this.kpiHistories = kpiHistories;
	}

	public Float getIndirectConsumption() {
		return indirectConsumption;
	}

	public void setIndirectConsumption(Float indirectConsumption) {
		this.indirectConsumption = indirectConsumption;
	}

	public Float getIndirectIndex() {
		return indirectIndex;
	}

	public void setIndirectIndex(Float indexAuxiliary) {
		this.indirectIndex = indexAuxiliary;
	}

	@Override
	public OrderIndexDTO getDTO(String... camposIgnorados) {
		OrderIndexDTO dto = new OrderIndexDTO();
		BeanUtils.copyProperties(this, dto);

		if (this.getVariable() != null) {
			dto.setVariableDTO(this.getVariable().getDTO());
		}
		if (this.getOrder() != null) {
			dto.setOrderDTO(this.getOrder().getDTO());
		}
		if (this.getEquipment() != null) {
			dto.setEquipmentDTO(this.getEquipment().getDTO());
		}
		if (this.getMaterial() != null) {
			dto.setMaterialDTO(this.getMaterial().getDTO());
		}

		if (this.getUnitMeasure() != null) {
			dto.setUnitMeasureDTO(this.getUnitMeasure().getDTO());
		}

		return dto;
	}

}
