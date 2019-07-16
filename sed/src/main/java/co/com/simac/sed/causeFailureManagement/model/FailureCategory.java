package co.com.simac.sed.causeFailureManagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.causeFailureManagement.dto.FailureCauseDTO;
import co.com.simac.sed.causeFailureManagement.dto.FailureCategoryDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;
import co.com.simac.sed.util.Utilities;

/**
 * 
 * @author Maria Perdomo SIMAC
 *
 */
@Entity
@Table(name = "categoria_falla")
public class FailureCategory extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 7037620955066281015L;

	@Column(name = "nombre")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "categoria_falla_causa", joinColumns = {
			@JoinColumn(name = "categoria_falla_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "causa_falla_id", nullable = false, updatable = false) })
	private List<FailureCause> failureCauses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FailureCause> getFailureCauses() {
		return failureCauses;
	}

	public void setFailureCauses(List<FailureCause> failureCauses) {
		this.failureCauses = failureCauses;
	}

	@Override
	public FailureCategoryDTO getDTO(String... camposIgnorados) {
		FailureCategoryDTO dto = new FailureCategoryDTO();
		BeanUtils.copyProperties(this, dto);

		if (failureCauses != null && !failureCauses.isEmpty()) {
			List<FailureCauseDTO> causesFailureDTO = Utilities.toDTOList(failureCauses, FailureCauseDTO.class);
			dto.setFailureCausesDTO(causesFailureDTO);
		}else {
			dto.setFailureCausesDTO(new ArrayList<>());
		}

		return dto;
	}
}
