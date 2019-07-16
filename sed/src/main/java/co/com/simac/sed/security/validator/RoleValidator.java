package co.com.simac.sed.security.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.com.simac.sed.security.dto.RoleDTO;
import co.com.simac.sed.security.service.RoleService;

@Component
public class RoleValidator implements Validator {

	@Autowired
	private RoleService roleService;

	@Override
	public boolean supports(Class<?> target) {
		return RoleDTO.class.isAssignableFrom(target);
	}

	@Override
	public void validate(Object object, Errors errors) {
		if (object != null) {
			RoleDTO dto = (RoleDTO) object;
			if (dto.getId() != null) {
				/**
				 * Validación de usuario existente
				 */
				RoleDTO roleDTO = roleService.getRoleById(dto.getId());
				if (!roleDTO.getName().equals(dto.getName())) {
					roleDTO = roleService.getRoleByName(dto.getName());
					if (roleDTO != null) {
						errors.rejectValue("", "400", "Ya existe un rol con el mismo nombre");
					}
				}
			} else {
				/**
				 * Validación de usuario nuevo
				 */
				RoleDTO roleDTO = roleService.getRoleByName(dto.getName());
				if (roleDTO != null) {
					errors.rejectValue("", "400", "Ya existe un rol con el mismo nombre");
				}
			}
		}
	}
}
