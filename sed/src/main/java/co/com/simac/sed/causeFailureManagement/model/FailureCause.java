package co.com.simac.sed.causeFailureManagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.causeFailureManagement.dto.FailureCauseDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria Perdomo SIMAC
 *
 */
@Entity
@Table(name = "causa_falla")
public class FailureCause extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "descripcion")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "failureCauses")
	private List<FailureCategory> failuresCategory;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FailureCategory> getFailuresCategory() {
		return failuresCategory;
	}

	public void setFailuresCategory(List<FailureCategory> failuresCategory) {
		this.failuresCategory = failuresCategory;
	}

	@Override
	public FailureCauseDTO getDTO(String... camposIgnorados) {
		FailureCauseDTO dto = new FailureCauseDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
