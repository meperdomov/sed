package co.com.simac.sed.security.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.com.simac.sed.security.dto.UserDTO;
import co.com.simac.sed.security.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> target) {
		return UserDTO.class.isAssignableFrom(target);
	}

	@Override
	public void validate(Object object, Errors errors) {
		if (object != null) {
			UserDTO dto = (UserDTO) object;
			if (dto.getId() != null) {
				/**
				 * Validación de usuario existente
				 */
				UserDTO userDTO = userService.getUserById(dto.getId());
				if (!userDTO.getUserName().equals(dto.getUserName())) {
					userDTO = userService.getUserByUserName(dto.getUserName());
					if (userDTO != null) {
						errors.rejectValue("", "400", "Ya existe un usuario con el mismo nombre de usuario");
					}
				}
			} else {
				/**
				 * Validación de usuario nuevo
				 */
				UserDTO userDTO = userService.getUserByUserName(dto.getUserName());
				if (userDTO != null) {
					errors.rejectValue("", "400", "Ya existe un usuario con el mismo nombre de usuario");
				}

				if (dto.getPassword() == null || dto.getPassword() == "") {
					errors.rejectValue("", "400", "El campo password no debe ser vacio");
				}

				if (dto.getRoleDTO() == null) {
					errors.rejectValue("", "400", "El rol es un campo requerido");
				}
			}
		}
	}
}
