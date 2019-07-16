package co.com.simac.sed.auxiliaryservicesmanagement.controller;

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

import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceConfigurationDTO;
import co.com.simac.sed.auxiliaryservicesmanagement.service.AuxiliaryServiceConfigurationService;
import co.com.simac.sed.auxiliaryservicesmanagement.validator.AuxiliaryServiceConfigurationValidator;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/auxiliaryserviceconfig")
public class AuxiliaryServiceConfigurationController {

	@Autowired
	private AuxiliaryServiceConfigurationService auxiliaryServiceConfigService;

	@Autowired
	private AuxiliaryServiceConfigurationValidator auxiliaryServiceConfigurationValidator;

	@PreAuthorize("hasRole('ROLE_AUXSERVICECONFIG_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<AuxiliaryServiceConfigurationDTO> getAuxiliaryServiceConfigByAuxiliaryIdList(@RequestParam("auxiliaryServiceId") Long auxiliaryServiceId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		
		return auxiliaryServiceConfigService.getAuxiliaryServiceConfigByAuxiliaryServiceId(auxiliaryServiceId, page, size);
	}

	@PreAuthorize("hasRole('ROLE_AUXSERVICECONFIG_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AuxiliaryServiceConfigurationDTO> getAuxiliaryServiceConfigById(@PathVariable("id") long id) {
		AuxiliaryServiceConfigurationDTO auxiliaryServiceConfigDTO = auxiliaryServiceConfigService
				.getAuxiliarServiceConfigById(id);
		if (auxiliaryServiceConfigDTO == null) {
			return new ResponseEntity<AuxiliaryServiceConfigurationDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AuxiliaryServiceConfigurationDTO>(auxiliaryServiceConfigDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_AUXSERVICECONFIG_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody AuxiliaryServiceConfigurationDTO auxiliaryServiceConfigDTO,
			Errors errors) {
		auxiliaryServiceConfigurationValidator.validate(auxiliaryServiceConfigDTO, errors);
		if (!errors.hasErrors()) {
			AuxiliaryServiceConfigurationDTO dto = auxiliaryServiceConfigService.save(auxiliaryServiceConfigDTO)
					.getDTO();
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_AUXSERVICECONFIG_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		auxiliaryServiceConfigService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}