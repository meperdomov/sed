package co.com.simac.sed.causeFailureManagement.controller;

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

import co.com.simac.sed.causeFailureManagement.dto.FailureCauseDTO;
import co.com.simac.sed.causeFailureManagement.service.FailureCauseService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/failurecause")
public class FailureCauseController {

	@Autowired
	private FailureCauseService causeFailureService;

	@PreAuthorize("hasRole('ROLE_FAILURE_CAUSE_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<FailureCauseDTO> getFailureCauseList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return causeFailureService.getFailureCausePageDTO(page, size);
	}

	@PreAuthorize("hasRole('ROLE_FAILURE_CAUSE_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<FailureCauseDTO> getFailureCause(@PathVariable("id") long id) {
		FailureCauseDTO failureCauseDTO = causeFailureService.getFailureCauseById(id);
		if (failureCauseDTO == null) {
			return new ResponseEntity<FailureCauseDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FailureCauseDTO>(failureCauseDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_FAILURE_CAUSE_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody FailureCauseDTO failureCauseDTO, Errors errors) {

		if (!errors.hasErrors()) {
			FailureCauseDTO dto = causeFailureService.save(failureCauseDTO).getDTO();
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_FAILURE_CAUSE_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		causeFailureService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}