package co.com.simac.sed.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.cube.dto.OrderIndexDTO;
import co.com.simac.sed.report.dto.KPIHistoryDTO;
import co.com.simac.sed.report.pdf.ExportToPdf;
import co.com.simac.sed.report.service.KPIHistoryService;
import co.com.simac.sed.util.PageDTO;

@RestController
@RequestMapping("/report/kpihistory")
public class KPIHistoryController {

	@Autowired
	private KPIHistoryService kpiHistoryService;

	@PreAuthorize("hasRole('ROLE_KPIHISTORY_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<KPIHistoryDTO> getKPIHistoryDTOList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "idKpiIndicator", defaultValue = "-1") long idKpiIndicator,
			@RequestParam(value = "idUnitMeasure", defaultValue = "-1") long idUnitMeasure,
			@RequestParam(value = "initialDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date initialDate,
			@RequestParam(value = "finalDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date finalDate) {
		return kpiHistoryService.getKPIHistoryPageDTO(page, size, idKpiIndicator, idUnitMeasure, initialDate,
				finalDate);
	}

	@PreAuthorize("hasRole('ROLE_KPIHISTORY_GET')")
	@RequestMapping(value = "{idKpiHistory}", method = RequestMethod.GET)
	public ResponseEntity<KPIHistoryDTO> getKPIHistory(@PathVariable("idKpiHistory") long id) {
		KPIHistoryDTO kpiHistoryDTO = kpiHistoryService.findById(id);
		if (kpiHistoryDTO == null) {
			return new ResponseEntity<KPIHistoryDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KPIHistoryDTO>(kpiHistoryDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_KPIHISTORY_GET')")
	@RequestMapping(value = "/{idKpiHistory}/ordersindex", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDTO<OrderIndexDTO> getOrdersIndexByIndicator(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@PathVariable("idKpiHistory") long idKpiHistory) {
		return kpiHistoryService.getOrdersIndexByKPIHistoryPageDTO(page, size, idKpiHistory);
	}
	
	
	//Reporte pdf
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> KPIHistoryReport(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "idKpiIndicator", defaultValue = "-1") long idKpiIndicator,
			@RequestParam(value = "idUnitMeasure", defaultValue = "-1") long idUnitMeasure,
			@RequestParam(value = "initialDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date initialDate,
			@RequestParam(value = "finalDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date finalDate) throws IOException {

		PageDTO<KPIHistoryDTO> resultPage=kpiHistoryService.getKPIHistoryPageDTO(page, size, idKpiIndicator, idUnitMeasure, initialDate,
				finalDate);
        List<KPIHistoryDTO> kpiHistoryDTOList = resultPage.getResults();

        ByteArrayInputStream bis = ExportToPdf.KPIHistoryReport(kpiHistoryDTOList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}