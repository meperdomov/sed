package co.com.simac.sed.general.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.com.simac.sed.general.dto.ParameterConfigurationDTO;
import co.com.simac.sed.general.model.ParameterConfiguration;
import co.com.simac.sed.general.service.ParameterConfigurationService;

@Component
public class ParameterConfigurationValidator implements Validator {

	@Autowired
	private ParameterConfigurationService parameterConfigurationService;

	@Override
	public boolean supports(Class<?> target) {
		return ParameterConfigurationDTO.class.isAssignableFrom(target);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		if (object != null) {
			ParameterConfigurationDTO dto = (ParameterConfigurationDTO) object;

			ParameterConfiguration parameter = parameterConfigurationService
					.getParameterConfigurationByKey(dto.getKey());

			if (parameter != null) {
				errors.rejectValue("key", "400",
						"Ya existe un parametro con este nombre");
			}
		}
	}
}
