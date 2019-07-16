package co.com.simac.sed.cube.dto;

import co.com.simac.sed.mvc.dto.DTOIdentidad;

public class QuantityDTO extends DTOIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -8146591353963146195L;

	private int numberSuccessfulOrders;

	private int numberOrdersError;

	public int getNumberSuccessfulOrders() {
		return numberSuccessfulOrders;
	}

	public void setNumberSuccessfulOrders(int numberSuccessfulOrders) {
		this.numberSuccessfulOrders = numberSuccessfulOrders;
	}

	public int getNumberOrdersError() {
		return numberOrdersError;
	}

	public void setNumberOrdersError(int numberOrdersError) {
		this.numberOrdersError = numberOrdersError;
	}

}
