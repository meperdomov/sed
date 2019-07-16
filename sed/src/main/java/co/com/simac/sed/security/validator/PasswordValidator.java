package co.com.simac.sed.security.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.com.simac.sed.security.dto.UserDTO;

@Component
public class PasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> target) {
		return UserDTO.class.isAssignableFrom(target);
	}

	@Override
	public void validate(Object object, Errors errors) {
		if (object != null) {
			UserDTO dto = (UserDTO) object;
			if (dto.getPassword() == null || dto.getPassword() == "") {
				errors.rejectValue("", "400", "El campo password no debe ser vacio");
			}
		}
	}
}
