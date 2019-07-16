package co.com.simac.sed.kpiindicatormanagement.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.com.simac.sed.kpiindicatormanagement.dto.KPIIndicatorDTO;

@Component
public class KPIIndicatorValidator implements Validator {

	@Override
	public boolean supports(Class<?> target) {
		return KPIIndicatorDTO.class.isAssignableFrom(target);
	}

	@Override
	public void validate(Object object, Errors errors) {
		if (object != null) {
			KPIIndicatorDTO dto = (KPIIndicatorDTO) object;

			if (dto.getEquipmentDTO() == null && dto.getRouteDTO() == null
					&& (dto.getStage() == null || dto.getStage().isEmpty())) {
				errors.rejectValue("percent", "400",
						"El indicador debe ser al menos de un equipo, una ruta o una etapa");
			}

		}
	}
}
