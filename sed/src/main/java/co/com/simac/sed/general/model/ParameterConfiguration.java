package co.com.simac.sed.general.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.general.dto.ParameterConfigurationDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "configuracion_parametros")
public class ParameterConfiguration extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "clave", nullable = false)
	private String key;

	@Column(name = "valor", nullable = false)
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

	@Override
	public ParameterConfigurationDTO getDTO(String... camposIgnorados) {
		ParameterConfigurationDTO dto = new ParameterConfigurationDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}