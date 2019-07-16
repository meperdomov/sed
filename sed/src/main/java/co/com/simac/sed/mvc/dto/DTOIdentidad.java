package co.com.simac.sed.mvc.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public abstract class DTOIdentidad implements DTOGenerico, Cloneable {

	private static final long serialVersionUID = 6775839587560909273L;
	private Long id;

	public Long getId() {
		return this.id;
	}

	@JsonDeserialize(as = Long.class)
	public void setId(Serializable id) {
		this.id = ((Long) id);
	}

	public DTOIdentidad clone() {
		try {
			return (DTOIdentidad) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}
}
