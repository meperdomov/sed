package co.com.simac.sed.cube.dto;

import co.com.simac.sed.kpiindicatormanagement.dto.EquipmentDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.MaterialDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.UnitMeasureDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.VariableDTO;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class OrderIndexDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -8146591353963146195L;

	private Float directConsumption;

	private Float indirectConsumption;

	private Float quantity;

	private Float directIndex;

	private Float indirectIndex;

	private VariableDTO variableDTO;

	private UnitMeasureDTO unitMeasureDTO;

	private OrderDTO orderDTO;

	private EquipmentDTO equipmentDTO;

	private MaterialDTO materialDTO;

	private String stage;

	public Float getDirectConsumption() {
		return directConsumption;
	}

	public void setDirectConsumption(Float directConsumption) {
		this.directConsumption = directConsumption;
	}

	public Float getIndirectConsumption() {
		return indirectConsumption;
	}

	public void setIndirectConsumption(Float indirectConsumption) {
		this.indirectConsumption = indirectConsumption;
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

	public void setDirectIndex(Float directIndex) {
		this.directIndex = directIndex;
	}

	public Float getIndirectIndex() {
		return indirectIndex;
	}

	public void setIndirectIndex(Float indirectIndex) {
		this.indirectIndex = indirectIndex;
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

	public OrderDTO getOrderDTO() {
		return orderDTO;
	}

	public void setOrderDTO(OrderDTO orderDTO) {
		this.orderDTO = orderDTO;
	}

	public EquipmentDTO getEquipmentDTO() {
		return equipmentDTO;
	}

	public void setEquipmentDTO(EquipmentDTO equipmentDTO) {
		this.equipmentDTO = equipmentDTO;
	}

	public MaterialDTO getMaterialDTO() {
		return materialDTO;
	}

	public void setMaterialDTO(MaterialDTO materialDTO) {
		this.materialDTO = materialDTO;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

}
