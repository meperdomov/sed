package co.com.simac.sed.alarmsmanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.alarmsmanagement.dto.AlarmTypeDTO;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "tipo_alarma")
public class AlarmType extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nombre", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "alarmType", cascade = CascadeType.ALL)
	private List<KPIIndicatorAlarm> kpiIndicatorAlarms;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKpiIndicatorAlarms(List<KPIIndicatorAlarm> kpiIndicatorAlarms) {
		this.kpiIndicatorAlarms = kpiIndicatorAlarms;
	}

	public List<KPIIndicatorAlarm> getKpiIndicatorAlarms() {
		return kpiIndicatorAlarms;
	}

	@Override
	public AlarmTypeDTO getDTO(String... camposIgnorados) {
		AlarmTypeDTO dto = new AlarmTypeDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}