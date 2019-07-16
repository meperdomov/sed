package co.com.simac.sed.systemalarms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.alarmsmanagement.model.KPIIndicatorAlarm;
import co.com.simac.sed.causeFailureManagement.model.FailureCategory;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;
import co.com.simac.sed.systemalarms.dto.AlarmHistoryDTO;

/**
 * 
 * @author Maria Perdomo SIMAC
 *
 */
@Entity
@Table(name = "historico_alarma")
public class AlarmHistory extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fecha_inicio", nullable = false)
	private Date startDate;

	@Column(name = "fecha_fin", nullable = false)
	private Date finalDate;

	@Column(name = "descripcion")
	private String description;

	@Column(name = "causa")
	private String cause;

	@Column(name = "prioridad", nullable = true)
	private String priority;

	@Column(name = "estado")
	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "categoria_falla_id", nullable = true)
	private FailureCategory failureCategory;

	@ManyToOne
	@JoinColumn(name = "alarma_indicadorkpi_id", nullable = true)
	private KPIIndicatorAlarm kpiIndicatorAlarm;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public FailureCategory getFailureCategory() {
		return failureCategory;
	}

	public void setFailureCategory(FailureCategory failureCategory) {
		this.failureCategory = failureCategory;
	}

	public KPIIndicatorAlarm getKpiIndicatorAlarm() {
		return kpiIndicatorAlarm;
	}

	public void setKpiIndicatorAlarm(KPIIndicatorAlarm kpiIndicatorAlarm) {
		this.kpiIndicatorAlarm = kpiIndicatorAlarm;
	}

	

	@Override
	public AlarmHistoryDTO getDTO(String... camposIgnorados) {
		AlarmHistoryDTO dto = new AlarmHistoryDTO();
		BeanUtils.copyProperties(this, dto);
		if (this.getFailureCategory() != null) {
			dto.setFailureCategoryDTO(this.getFailureCategory().getDTO());
		}
		if (this.getKpiIndicatorAlarm() != null) {
			dto.setKpiIndicatorAlarmDTO(this.getKpiIndicatorAlarm().getDTO());
		}
		return dto;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}
