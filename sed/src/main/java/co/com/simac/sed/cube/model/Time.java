package co.com.simac.sed.cube.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.simac.sed.mvc.dto.DTOGenerico;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;

/**
 * 
 * @author Juan Camilo Jaramillo
 *
 */
@Entity
@Table(name = "tiempo")
public class Time extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -6592783057815792449L;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", nullable = true)
	private Date date;

	@Column(name = "dia", nullable = true)
	private int day;

	@Column(name = "mes", nullable = true)
	private int month;

	@Column(name = "annio", nullable = true)
	private int year;

	@Column(name = "trimestre", nullable = true)
	private int quarter;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	@Override
	public DTOGenerico getDTO(String... camposIgnorados) {
		return null;
	}

}
