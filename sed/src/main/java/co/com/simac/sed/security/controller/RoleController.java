package co.com.simac.sed.security.controller;

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

import co.com.simac.sed.security.dto.RoleDTO;
import co.com.simac.sed.security.service.RoleService;
import co.com.simac.sed.security.validator.RoleValidator;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleValidator roleValidator;

	@PreAuthorize("hasRole('ROLE_ROLE_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<RoleDTO> getRoleDTOList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return roleService.getRolePageDTO(page, size);
	}

	@PreAuthorize("hasRole('ROLE_ROLE_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<RoleDTO> getRole(@PathVariable("id") long id) {
		RoleDTO roleDTO = roleService.getRoleById(id);
		if (roleDTO == null) {
			return new ResponseEntity<RoleDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RoleDTO>(roleDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ROLE_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody RoleDTO roleDTO,
			Errors errors) {
		roleValidator.validate(roleDTO, errors);
		if (!errors.hasErrors()) {
			RoleDTO dto = roleService.save(roleDTO).getDTO();
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(
				ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_ROLE_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		roleService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}