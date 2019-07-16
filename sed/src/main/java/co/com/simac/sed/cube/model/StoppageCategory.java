package co.com.simac.sed.cube.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.simac.sed.mvc.dto.DTOGenerico;
import co.com.simac.sed.mvc.modelo.EntidadCubo;

/**
 * 
 * @author Juan Camilo Jaramillo Alzate
 *
 */

@Entity
@Table(name = "categoria_paro")
public class StoppageCategory extends EntidadCubo {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -1226230914628351813L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private OEECategory oeeCategory;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stoppageCategory", cascade = CascadeType.ALL)
	private List<StoppageType> stoppageTypes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OEECategory getOeeCategory() {
		return oeeCategory;
	}

	public void setOeeCategory(OEECategory oeeCategory) {
		this.oeeCategory = oeeCategory;
	}

	public List<StoppageType> getStoppageTypes() {
		return stoppageTypes;
	}

	public void setStoppageTypes(List<StoppageType> stoppageTypes) {
		this.stoppageTypes = stoppageTypes;
	}

	@Override
	public DTOGenerico getDTO(String... camposIgnorados) {
		return null;
	}
}
