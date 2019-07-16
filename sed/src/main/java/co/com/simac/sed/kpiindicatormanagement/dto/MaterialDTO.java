package co.com.simac.sed.kpiindicatormanagement.dto;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class MaterialDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

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
