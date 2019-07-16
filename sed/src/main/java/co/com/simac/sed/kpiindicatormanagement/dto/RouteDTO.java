package co.com.simac.sed.kpiindicatormanagement.dto;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class RouteDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	private String name;
	
	private MaterialDTO materialDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public MaterialDTO getMaterialDTO() {
		return materialDTO;
	}
	
	public void setMaterialDTO(MaterialDTO materialDTO) {
		this.materialDTO = materialDTO;
	}

}
