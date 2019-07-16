package co.com.simac.sed.alarmsmanagement.dto;

import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class AlarmTypeDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	@NotBlank(message = "El campo nombre no puede ser vacio")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
