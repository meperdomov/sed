package co.com.simac.sed.cube.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.simac.sed.mvc.dto.DTOGenerico;
import co.com.simac.sed.mvc.modelo.EntidadCubo;

/**
 * 
 * @author Juan Camilo Jaramillo Alzate
 *
 */

@Entity
@Table(name = "tipo_paro")
public class StoppageType extends EntidadCubo {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -561055045147546521L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "codigo", nullable = false)
	private String code;

	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private StoppageCategory stoppageCategory;

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

	public StoppageCategory getStoppageCategory() {
		return stoppageCategory;
	}

	public void setStoppageCategory(StoppageCategory stoppageCategory) {
		this.stoppageCategory = stoppageCategory;
	}

	@Override
	public DTOGenerico getDTO(String... camposIgnorados) {
		return null;
	}

}
