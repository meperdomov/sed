package co.com.simac.sed.kpiindicatormanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.kpiindicatormanagement.dto.KPIIndicatorDTO;
import co.com.simac.sed.kpiindicatormanagement.service.KPIIndicatorService;
import co.com.simac.sed.kpiindicatormanagement.validator.KPIIndicatorValidator;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/kpiindicator")
public class KPIIndicatorController {

	@Autowired
	private KPIIndicatorService kpiIndicatorService;

	@Autowired
	private KPIIndicatorValidator kpiIndicatorValidator;

	@PreAuthorize("hasRole('ROLE_KPIINDICATOR_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<KPIIndicatorDTO> getKPIIndicatorDTOList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return kpiIndicatorService.getKpiIndicatorPageDTO(page, size);
	}

	@PreAuthorize("hasRole('ROLE_KPIINDICATOR_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<KPIIndicatorDTO> getKPIIndicator(
			@PathVariable("id") long id) {
		KPIIndicatorDTO kpiIndicatorDTO = kpiIndicatorService
				.getKpiIndicatorById(id);
		if (kpiIndicatorDTO == null) {
			return new ResponseEntity<KPIIndicatorDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KPIIndicatorDTO>(kpiIndicatorDTO,
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_KPIINDICATOR_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(
			@Valid @RequestBody KPIIndicatorDTO kpiIndicatorDTO, Errors errors) {
		kpiIndicatorValidator.validate(kpiIndicatorDTO, errors);
		if (!errors.hasErrors()) {
			KPIIndicatorDTO dto = kpiIndicatorService.save(kpiIndicatorDTO)
					.getDTO();
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(
				ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_KPIINDICATOR_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		kpiIndicatorService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}