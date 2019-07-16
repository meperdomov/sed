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

import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceDTO;
import co.com.simac.sed.auxiliaryservicesmanagement.service.AuxiliaryServiceService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/auxiliaryservices")
public class AuxiliaryServiceController {

	@Autowired
	private AuxiliaryServiceService auxiliaryServiceService;

	@PreAuthorize("hasRole('ROLE_AUXSERVICE_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<AuxiliaryServiceDTO> getAuxiliaryServiceDTOList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return auxiliaryServiceService.getAuxiliaryServicePageDTO(page, size);
	}

	@PreAuthorize("hasRole('ROLE_AUXSERVICE_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AuxiliaryServiceDTO> getAuxiliaryServiceById(
			@PathVariable("id") long id) {
		AuxiliaryServiceDTO auxiliaryServiceDTO = auxiliaryServiceService
				.getAuxiliarServiceById(id);
		if (auxiliaryServiceDTO == null) {
			return new ResponseEntity<AuxiliaryServiceDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AuxiliaryServiceDTO>(auxiliaryServiceDTO,
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_AUXSERVICE_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(
			@Valid @RequestBody AuxiliaryServiceDTO auxiliaryServiceDTO,
			Errors errors) {

		if (!errors.hasErrors()) {
			AuxiliaryServiceDTO dto = auxiliaryServiceService.save(
					auxiliaryServiceDTO).getDTO();

			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(
				ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_AUXSERVICE_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		auxiliaryServiceService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}