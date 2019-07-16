package co.com.simac.sed.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.security.dto.UserDTO;
import co.com.simac.sed.security.service.UserService;
import co.com.simac.sed.security.validator.PasswordValidator;
import co.com.simac.sed.security.validator.UserValidator;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private PasswordValidator passwordValidator;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getAuthenticatedUser(OAuth2Authentication authentication) {
		User user = (User) authentication.getUserAuthentication().getPrincipal();

		UserDTO userDTO = userService.getUserByUserName(user.getUsername());
		if (userDTO == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<UserDTO> getUserDTOList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return userService.getUserPageDTO(page, size, "password");
	}

	@PreAuthorize("hasRole('ROLE_USER_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
		UserDTO userDTO = userService.getUserById(id, "password");
		if (userDTO == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody UserDTO userDTO, Errors errors) {
		userValidator.validate(userDTO, errors);
		if (!errors.hasErrors()) {
			UserDTO dto = userService.save(userDTO).getDTO("password");
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_USER_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public ResponseEntity updateUserPassword(@RequestBody UserDTO userDTO) {
		Errors errors = new BeanPropertyBindingResult(userDTO, "userDTO");
		passwordValidator.validate(userDTO, errors);
		if (!errors.hasErrors()) {
			int updatedRows = userService.updateUserPassword(userDTO.getPassword(), userDTO.getId());
			if (updatedRows > 0) {
				return new ResponseEntity(userDTO, HttpStatus.OK);
			}
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

}