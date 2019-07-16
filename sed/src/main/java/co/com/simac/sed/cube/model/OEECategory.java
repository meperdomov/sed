package co.com.simac.sed.cube.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.simac.sed.mvc.dto.DTOGenerico;
import co.com.simac.sed.mvc.modelo.EntidadCubo;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "categoria_oee")
public class OEECategory extends EntidadCubo {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 3005580544739104283L;
	
	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "indicador", nullable = false)
	private String indicator;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "oeeCategory", cascade = CascadeType.ALL)
	private List<StoppageCategory> stoppageCategories;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public List<StoppageCategory> getStoppageCategories() {
		return stoppageCategories;
	}

	public void setStoppageCategories(List<StoppageCategory> stoppageCategories) {
		this.stoppageCategories = stoppageCategories;
	}

	@Override
	public DTOGenerico getDTO(String... camposIgnorados) {
		return null;
	}
	
}
