package co.com.simac.sed.cube.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.cube.dto.MeasurerDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "medidor")
public class Measurer extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "codigo", nullable = false)
	private int code;

	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "consumo_teorico", nullable = true)
	private float referenceConsumption;

	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipment equipment;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	@Override
	public MeasurerDTO getDTO(String... camposIgnorados) {
		MeasurerDTO dto = new MeasurerDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

	public float getReferenceConsumption() {
		return referenceConsumption;
	}

	public void setReferenceConsumption(float referenceConsumption) {
		this.referenceConsumption = referenceConsumption;
	}
}
