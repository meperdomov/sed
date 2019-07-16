package co.com.simac.sed.graphics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.graphics.dto.ConsumptionProductionGraphDTO;
import co.com.simac.sed.graphics.service.ConsumptionProductionGraphService;

@RestController
@RequestMapping("/graphic/consumptionproduction")
public class ConsumptionProductionGraphController {

	@Autowired
	private ConsumptionProductionGraphService consumptionProductionService;

	@PreAuthorize("hasRole('ROLE_CONSUMPTIONPRODUCTION_GRAPH')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ConsumptionProductionGraphDTO> getSerieConsumptionProduction(
			@RequestParam(value = "idKpiHistory", defaultValue = "-1") long idKpiHistory) {
		ConsumptionProductionGraphDTO consumptionProductionGraphDTO = new ConsumptionProductionGraphDTO();
		consumptionProductionGraphDTO = consumptionProductionService
				.getConsumptionProductionGraph(idKpiHistory);

		return new ResponseEntity<ConsumptionProductionGraphDTO>(
				consumptionProductionGraphDTO, HttpStatus.OK);
	}

}