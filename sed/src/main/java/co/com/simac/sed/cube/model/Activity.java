package co.com.simac.sed.cube.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.simac.sed.mvc.dto.DTOGenerico;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "actividad")
public class Activity extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "abreviacion_rb", nullable = false)
	private String abbreviationRB;

	@Column(name = "abreviacion_ri", nullable = false)
	private String abbreviationRI;

	@Column(name = "codigo_producto", nullable = false)
	private String productCode;

	@Column(name = "estado", nullable = false)
	private String state;

	@Column(name = "fecha_ingreso", nullable = true)
	private Date startDate;

	@Column(name = "fecha_salida", nullable = true)
	private Date endDate;

	@Column(name = "nombre_paso", nullable = false)
	private String stepName;

	@Column(name = "nombre_ruta", nullable = false)
	private String routeName;

	@ManyToOne
	@JoinColumn(name = "orden_id")
	private Order order;

	@Column(name = "paso_siguiente_id", nullable = false)
	private Long nextStepId;

	@Column(name = "paso_anterior_id", nullable = false)
	private Long previousStepId;

	@Column(name = "evento_ingreso_id", nullable = false)
	private Long eventEntryId;

	@Column(name = "mensaje_id", nullable = false)
	private Long messageId;

	@Column(name = "pe_codigo")
	private String stoppageCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipo_id")
	private Equipment equipment;

	@Column(name = "ejecucion_rb_id", nullable = false)
	private Long executionRBId;

	@Column(name = "ejecucion_ri_id", nullable = false)
	private Long executionRIId;

	@Column(name = "duracion_estimada", nullable = false)
	private Float estimatedDuration;

	@Column(name = "duracion", nullable = true)
	private Float duration;

	@ManyToOne
	@JoinColumn(name = "ruta_id")
	private Route route;

	@ManyToOne
	@JoinColumn(name = "tiempo_id")
	private Time time;

	public String getAbbreviationRB() {
		return abbreviationRB;
	}

	public void setAbbreviationRB(String abbreviationRB) {
		this.abbreviationRB = abbreviationRB;
	}

	public String getAbbreviationRI() {
		return abbreviationRI;
	}

	public void setAbbreviationRI(String abbreviationRI) {
		this.abbreviationRI = abbreviationRI;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getNextStepId() {
		return nextStepId;
	}

	public void setNextStepId(Long nextStepId) {
		this.nextStepId = nextStepId;
	}

	public Long getPreviousStepId() {
		return previousStepId;
	}

	public void setPreviousStepId(Long previousStepId) {
		this.previousStepId = previousStepId;
	}

	public Long getEventEntryId() {
		return eventEntryId;
	}

	public void setEventEntryId(Long eventEntryId) {
		this.eventEntryId = eventEntryId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getStoppageCode() {
		return stoppageCode;
	}

	public void setStoppageCode(String stoppageCode) {
		this.stoppageCode = stoppageCode;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Long getExecutionRBId() {
		return executionRBId;
	}

	public void setExecutionRBId(Long executionRBId) {
		this.executionRBId = executionRBId;
	}

	public Long getExecutionRIId() {
		return executionRIId;
	}

	public void setExecutionRIId(Long executionRIId) {
		this.executionRIId = executionRIId;
	}

	public float getEstimatedDuration() {
		return estimatedDuration;
	}

	public void setEstimatedDuration(float estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public DTOGenerico getDTO(String... camposIgnorados) {
		return null;
	}
}
