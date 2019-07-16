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

import co.com.simac.sed.causeFailureManagement.dto.FailureCategoryDTO;
import co.com.simac.sed.causeFailureManagement.service.FailureCategoryService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/failurecategory")
public class FailureCategoryController {

	@Autowired
	private FailureCategoryService failureCategoryService;

	@PreAuthorize("hasRole('ROLE_FAILURE_CATEGORY_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<FailureCategoryDTO> getFailureCategoryList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return failureCategoryService.getFailureCategoryPageDTO(page, size);
	}

	@PreAuthorize("hasRole('ROLE_FAILURE_CATEGORY_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<FailureCategoryDTO> getFailureCategory(@PathVariable("id") long id) {
		FailureCategoryDTO failureCategoryDTO = failureCategoryService.getFailureCategoryById(id);
		if (failureCategoryDTO == null) {
			return new ResponseEntity<FailureCategoryDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FailureCategoryDTO>(failureCategoryDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_FAILURE_CATEGORY_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody FailureCategoryDTO failureCategoryDTO, Errors errors) {

		if (!errors.hasErrors()) {
			FailureCategoryDTO dto = failureCategoryService.save(failureCategoryDTO).getDTO();
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_FAILURE_CATEGORY_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		failureCategoryService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}