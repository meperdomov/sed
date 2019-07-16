package co.com.simac.sed.auxiliaryservicesmanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceDTO;
import co.com.simac.sed.kpiindicatormanagement.model.Variable;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "servicio_auxiliar")
public class AuxiliaryService extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "variable_id")
	private Variable variable;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "auxiliaryService", cascade = CascadeType.ALL)
	private List<AuxiliaryServiceConfiguration> auxiliaryServiceConfigurations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public List<AuxiliaryServiceConfiguration> getAuxiliaryServiceConfigurations() {
		return auxiliaryServiceConfigurations;
	}
	
	public void setAuxiliaryServiceConfigurations(List<AuxiliaryServiceConfiguration> auxiliaryServiceConfigurations) {
		this.auxiliaryServiceConfigurations = auxiliaryServiceConfigurations;
	}
	
	@Override
	public AuxiliaryServiceDTO getDTO(String... camposIgnorados) {
		AuxiliaryServiceDTO dto = new AuxiliaryServiceDTO();
		BeanUtils.copyProperties(this, dto);
		if (this.getVariable() != null) {
			dto.setVariableDTO(this.getVariable().getDTO());
		}
		return dto;
	}

}
