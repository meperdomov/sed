package co.com.simac.sed.alarmsmanagement.dto;

import org.hibernate.validator.constraints.NotBlank;

import co.com.simac.sed.kpiindicatormanagement.dto.KPIIndicatorDTO;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class KPIIndicatorAlarmDTO extends DTOIdentidad {

	private static final long serialVersionUID = 6775839587560909273L;

	@NotBlank(message = "El campo mensaje no puede ser vacio")
	private String message;

	@NotBlank(message = "El campo prioridad no puede ser vacio")
	private String priority;

	private float factor;

	private float percentage;

	private float correlation;

	private AlarmTypeDTO alarmTypeDTO;

	private KPIIndicatorDTO kpiIndicatorDTO;

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

	public AlarmTypeDTO getAlarmTypeDTO() {
		return alarmTypeDTO;
	}

	public void setAlarmTypeDTO(AlarmTypeDTO alarmTypeDTO) {
		this.alarmTypeDTO = alarmTypeDTO;
	}

	public KPIIndicatorDTO getKpiIndicatorDTO() {
		return kpiIndicatorDTO;
	}

	public void setKpiIndicatorDTO(KPIIndicatorDTO kpiIndicatorDTO) {
		this.kpiIndicatorDTO = kpiIndicatorDTO;
	}

}
