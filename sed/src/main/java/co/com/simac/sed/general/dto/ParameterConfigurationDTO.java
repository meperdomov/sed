package co.com.simac.sed.general.dto;

import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

/**
 * 
 * @author Juan Camilo
 *
 */
public class ParameterConfigurationDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	@NotBlank(message = "La clave no puede ser vacia")
	private String key;

	@NotBlank(message = "El valor no puede ser vacio")
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
