package co.com.simac.sed.cube.dto;

import java.util.Date;

import co.com.simac.sed.kpiindicatormanagement.dto.RouteDTO;
import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class OrderDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -8146591353963146195L;

	private Long orderNumber;

	private float quantity;

	private boolean completed;

	private boolean completedWithError;

	private String plant;

	private RouteDTO routeDTO;

	private Date startDate;

	private Date endDate;

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
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

	public RouteDTO getRouteDTO() {
		return routeDTO;
	}

	public void setRouteDTO(RouteDTO routeDTO) {
		this.routeDTO = routeDTO;
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

}
