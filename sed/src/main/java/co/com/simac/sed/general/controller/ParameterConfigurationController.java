package co.com.simac.sed.general.controller;

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

import co.com.simac.sed.general.dto.ParameterConfigurationDTO;
import co.com.simac.sed.general.service.ParameterConfigurationService;
import co.com.simac.sed.general.validator.ParameterConfigurationValidator;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/parameterconf")
public class ParameterConfigurationController {

	@Autowired
	private ParameterConfigurationService parameterConfigurationService;
	
	@Autowired
	private ParameterConfigurationValidator parameterConfigurationValidator;

	@PreAuthorize("hasRole('ROLE_PARAMETER_CONF_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<ParameterConfigurationDTO> getConfigurationParametersList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return parameterConfigurationService.getParameterConfigurationPageDTO(page, size);
	}

	@PreAuthorize("hasRole('ROLE_PARAMETER_CONF_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<ParameterConfigurationDTO> getConfigurationParameters(@PathVariable("id") long id) {
		ParameterConfigurationDTO configurationParametersDTO = parameterConfigurationService
				.getParameterConfigurationById(id);
		if (configurationParametersDTO == null) {
			return new ResponseEntity<ParameterConfigurationDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ParameterConfigurationDTO>(configurationParametersDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_PARAMETER_CONF_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody ParameterConfigurationDTO parameterConfigurationDTO,
			Errors errors) {
		parameterConfigurationValidator.validate(parameterConfigurationDTO, errors);
		if (!errors.hasErrors()) {
			ParameterConfigurationDTO dto = parameterConfigurationService.save(parameterConfigurationDTO).getDTO();
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_PARAMETER_CONF_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		parameterConfigurationService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}