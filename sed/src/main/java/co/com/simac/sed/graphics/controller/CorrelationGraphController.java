package co.com.simac.sed.graphics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.graphics.dto.CorrelationGraphDTO;
import co.com.simac.sed.graphics.service.CorrelationGraphService;

@RestController
@RequestMapping("/graphic/correlation")
public class CorrelationGraphController {

	@Autowired
	private CorrelationGraphService correlationGraphKpiService;

	@PreAuthorize("hasRole('ROLE_CORRELATION_GRAPH')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<CorrelationGraphDTO> getCorrelationGraphByKPIHistory(
			@RequestParam(value = "idKpiHistory", defaultValue = "-1") long idKpiHistory) {
		CorrelationGraphDTO correlationGraphDTO = correlationGraphKpiService.getCorrelationGraph(idKpiHistory);
		return new ResponseEntity<CorrelationGraphDTO>(correlationGraphDTO, HttpStatus.OK);
	}

}