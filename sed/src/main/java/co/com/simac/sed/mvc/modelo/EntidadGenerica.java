package co.com.simac.sed.mvc.modelo;

import java.io.Serializable;

import co.com.simac.sed.mvc.dto.DTOGenerico;

public abstract class EntidadGenerica implements Serializable {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 8193992920232946654L;

	public abstract DTOGenerico getDTO(String... camposIgnorados);

}
