package co.com.simac.sed.systemalarms.dto;

import java.util.Date;

import co.com.simac.sed.alarmsmanagement.dto.KPIIndicatorAlarmDTO;
import co.com.simac.sed.causeFailureManagement.dto.FailureCategoryDTO;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

/**
 * 
 * @author Juan Camilo
 *
 */
public class AlarmHistoryDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = 1235532231524866451L;

	
	private Date startDate;

	
	private Date finalDate;

	
	private String description;

	
	private String cause;

	
	private Boolean status;
	
	private String color;

	
	private FailureCategoryDTO failureCategoryDTO;
	
	
	private KPIIndicatorAlarmDTO kpiIndicatorAlarmDTO;


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


	public FailureCategoryDTO getFailureCategoryDTO() {
		return failureCategoryDTO;
	}


	public void setFailureCategoryDTO(FailureCategoryDTO failureCategoryDTO) {
		this.failureCategoryDTO = failureCategoryDTO;
	}


	public KPIIndicatorAlarmDTO getKpiIndicatorAlarmDTO() {
		return kpiIndicatorAlarmDTO;
	}


	public void setKpiIndicatorAlarmDTO(KPIIndicatorAlarmDTO kpiIndicatorAlarmDTO) {
		this.kpiIndicatorAlarmDTO = kpiIndicatorAlarmDTO;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}

}
