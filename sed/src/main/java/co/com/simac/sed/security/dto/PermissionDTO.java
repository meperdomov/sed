package co.com.simac.sed.security.dto;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class PermissionDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 7924281671658326650L;

	private String name;

	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
