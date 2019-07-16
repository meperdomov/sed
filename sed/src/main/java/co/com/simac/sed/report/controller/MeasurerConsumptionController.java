package co.com.simac.sed.report.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.report.service.MeasurerConsumptionService;
import co.com.simac.sed.util.PageDTO;

@RestController
@RequestMapping("/report/consumption")
public class MeasurerConsumptionController {

	@Autowired
	private MeasurerConsumptionService measurerConsumptionService;

	@PreAuthorize("hasRole('ROLE_MEASURERCONSUMPTION_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<Object> getKPIHistoryDTOList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "idVariable", defaultValue = "-1") long idVariable,
			@RequestParam(value = "idEquipment", defaultValue = "-1") long idEquipment,
			@RequestParam(value = "idMeasurer", defaultValue = "-1") long idMeasurer,
			@RequestParam(value = "idUnitMeasure", defaultValue = "-1") long idUnitMeasure,
			@RequestParam(value = "initialDate", required = true) @DateTimeFormat(pattern = "dd-MM-yyyy") Date initialDate,
			@RequestParam(value = "finalDate", required = true) @DateTimeFormat(pattern = "dd-MM-yyyy") Date finalDate) {

		return measurerConsumptionService.getMeasurerConsumptionPage(page,
				size, idVariable, idEquipment, idMeasurer, idUnitMeasure,
				initialDate, finalDate);
	}

}