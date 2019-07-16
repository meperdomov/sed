package co.com.simac.sed.report.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.mvc.modelo.EntidadIdentidad;
import co.com.simac.sed.report.dto.KPIHistoryDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Entity
@Table(name = "historicokpi")
public class KPIHistory extends EntidadIdentidad {

	/**
	 * Serialversion
	 */
	private static final long serialVersionUID = -2682655851908095186L;

	@Column(name = "fecha_inicio", nullable = false)
	private Date startDate;

	@Column(name = "fecha_fin", nullable = false)
	private Date endDate;

	@Column(name = "valor_promedio", nullable = false)
	private double averageValue;

	@Column(name = "correlacion", nullable = false)
	private Double correlation;

	@Column(name = "desviacion", nullable = false)
	private Double deviation;

	@Column(name = "intercepto", nullable = true)
	private Double intercept;

	@Column(name = "pendiente", nullable = false)
	private Double slope;

	@Column(name = "servicio_auxiliar", nullable = false)
	private boolean auxiliaryService;

	@Column(name = "es_util", nullable = false)
	private boolean isUtil;

	@ManyToOne
	@JoinColumn(name = "indicador_kpi", nullable = false)
	private KPIIndicator kpiindicator;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "indices_historico_kpi", joinColumns = {
			@JoinColumn(name = "historico_kpi_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "orden_indice_id", nullable = false, updatable = false) })
	private List<OrderIndex> ordersIndex;

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

	public double getAverageValue() {
		return averageValue;
	}

	public void setAverageValue(double averageValue) {
		this.averageValue = averageValue;
	}

	public Double getCorrelation() {
		return correlation;
	}

	public void setCorrelation(Double correlation) {
		this.correlation = correlation;
	}

	public Double getDeviation() {
		return deviation;
	}

	public void setDeviation(Double deviation) {
		this.deviation = deviation;
	}

	public Double getIntercept() {
		return intercept;
	}

	public void setIntercept(Double intercept) {
		this.intercept = intercept;
	}

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	public boolean isAuxiliaryService() {
		return auxiliaryService;
	}

	public void setAuxiliaryService(boolean auxiliaryService) {
		this.auxiliaryService = auxiliaryService;
	}

	public KPIIndicator getKpiindicator() {
		return kpiindicator;
	}

	public void setKpiindicator(KPIIndicator kpiindicator) {
		this.kpiindicator = kpiindicator;
	}

	public List<OrderIndex> getOrdersIndex() {
		return ordersIndex;
	}

	public void setOrdersIndex(List<OrderIndex> ordersIndex) {
		this.ordersIndex = ordersIndex;
	}

	public boolean getUtil() {
		return isUtil;
	}

	public void setUtil(boolean isUtil) {
		this.isUtil = isUtil;
	}

	@Override
	public KPIHistoryDTO getDTO(String... camposIgnorados) {
		KPIHistoryDTO dto = new KPIHistoryDTO();
		BeanUtils.copyProperties(this, dto);

		if (this.getKpiindicator() != null) {
			dto.setKpiIndicatorDTO(this.getKpiindicator().getDTO());
		}
		return dto;
	}

}
