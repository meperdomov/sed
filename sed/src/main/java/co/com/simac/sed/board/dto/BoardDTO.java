package co.com.simac.sed.board.dto;

import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class BoardDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -8012470144748033770L;

	@NotBlank(message = "El campo slug no puede ser vacio")
	private String slug;

	@NotBlank(message = "El campo nombre no puede ser vacio")
	private String name;

	@NotBlank(message = "El campo configuración no puede ser vacio")
	private String configuration;

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

}
