package co.com.simac.sed.graphics.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.graphics.service.TrendGraphKPIIndicatorService;

@RestController
@RequestMapping("/graphic/trendkpi")
public class TrendGraphKPIIndicatorController {

	@Autowired
	private TrendGraphKPIIndicatorService trendGraphKpiService;

	@PreAuthorize("hasRole('ROLE_TRENDKPI_GRAPH')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Object[]>> getKPIHistoryDTOList(
			@RequestParam(value = "idKpiIndicator", defaultValue = "-1") long idKpiIndicator,
			@RequestParam(value = "idVariable", defaultValue = "-1") long idVariable,
			@RequestParam(value = "idUnitMeasure", defaultValue = "-1") long idUnitMeasure,
			@RequestParam(value = "initialDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date initialDate,
			@RequestParam(value = "finalDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date finalDate) {

		List<Object[]> series = trendGraphKpiService.getTrendGraph(idKpiIndicator, idVariable, idUnitMeasure,initialDate, finalDate);

		if (series.isEmpty()) {
			return new ResponseEntity<List<Object[]>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Object[]>>(series, HttpStatus.OK);
	}

}