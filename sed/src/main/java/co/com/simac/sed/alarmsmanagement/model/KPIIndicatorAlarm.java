package co.com.simac.sed.alarmsmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.alarmsmanagement.dto.KPIIndicatorAlarmDTO;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "alarma_indicadorkpi")
public class KPIIndicatorAlarm extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "mensaje", nullable = false)
	private String message;

	@Column(name = "prioridad", nullable = false)
	private String priority;

	@Column(name = "factor", nullable = false)
	private float factor = 1;

	@Column(name = "porcentaje", nullable = false)
	private float percentage = 100;

	@Column(name = "correlacion", nullable = false)
	private float correlation = 0.7f;

	@ManyToOne
	@JoinColumn(name = "tipo_alarma_id")
	private AlarmType alarmType;

	@ManyToOne
	@JoinColumn(name = "indicador_kpi", nullable = false)
	private KPIIndicator kpiIndicator;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public float getFactor() {
		return factor;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public float getCorrelation() {
		return correlation;
	}

	public void setCorrelation(float correlation) {
		this.correlation = correlation;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public KPIIndicator getKpiIndicator() {
		return kpiIndicator;
	}

	public void setKpiIndicator(KPIIndicator kpiIndicator) {
		this.kpiIndicator = kpiIndicator;
	}

	@Override
	public KPIIndicatorAlarmDTO getDTO(String... camposIgnorados) {
		KPIIndicatorAlarmDTO dto = new KPIIndicatorAlarmDTO();
		BeanUtils.copyProperties(this, dto);
		if (this.getAlarmType() != null) {
			dto.setAlarmTypeDTO(this.getAlarmType().getDTO());
		}
		if (this.getKpiIndicator() != null) {
			dto.setKpiIndicatorDTO(this.getKpiIndicator().getDTO());
		}
		return dto;
	}
}