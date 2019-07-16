package co.com.simac.sed.cube.dto;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class MeasurerDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -8146591353963146195L;

	private int code;

	private String name;
	
	private float referenceConsumption;

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

	public float getReferenceConsumption() {
		return referenceConsumption;
	}

	public void setReferenceConsumption(float referenceConsumption) {
		this.referenceConsumption = referenceConsumption;
	}

}
