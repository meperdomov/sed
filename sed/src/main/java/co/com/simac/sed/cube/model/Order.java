package co.com.simac.sed.cube.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.cube.dto.OrderDTO;
import co.com.simac.sed.mvc.modelo.EntidadCubo;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "orden_produccion")
public class Order extends EntidadCubo {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "nro_orden", nullable = false)
	private Long orderNumber;

	@Column(name = "fecha_inicio", nullable = true)
	private Date startDate;

	@Column(name = "fecha_fin", nullable = true)
	private Date endDate;

	@Column(name = "finalizada", nullable = false)
	private boolean completed;

	@Column(name = "fin_con_error", nullable = false)
	private boolean completedWithError;

	@Column(name = "planta", nullable = false)
	private String plant;

	@Column(name = "cantidad", nullable = false)
	private float quantity;

	@ManyToOne
	@JoinColumn(name = "ruta_id")
	private Route route;

	@ManyToOne
	@JoinColumn(name = "tiempo_id")
	private Time time;

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isCompletedWithError() {
		return completedWithError;
	}

	public void setCompletedWithError(boolean completedWithError) {
		this.completedWithError = completedWithError;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
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
	public OrderDTO getDTO(String... camposIgnorados) {
		OrderDTO dto = new OrderDTO();
		BeanUtils.copyProperties(this, dto);

		if (getRoute() != null) {
			dto.setRouteDTO(getRoute().getDTO());
		}
		return dto;
	}

}
