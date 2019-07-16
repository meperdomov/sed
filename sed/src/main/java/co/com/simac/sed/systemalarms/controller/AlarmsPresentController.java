package co.com.simac.sed.systemalarms.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.systemalarms.dto.AlarmHistoryDTO;
import co.com.simac.sed.systemalarms.service.AlarmsPresentService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/alarmspresent")
public class AlarmsPresentController {

	@Autowired
	private AlarmsPresentService alarmsPresentService;

	@PreAuthorize("hasRole('ROLE_ALARMPRESENT_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<AlarmHistoryDTO> getAlarmHistoryDTOList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "initialDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date initialDate,
			@RequestParam(value = "finalDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date finalDate) {
		return alarmsPresentService.getAlarmsPresentPageDTO(page, size, initialDate, finalDate);
	}

	@PreAuthorize("hasRole('ROLE_ALARMPRESENT_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody AlarmHistoryDTO alarmHistoryDTO, Errors errors) {

		if (!errors.hasErrors() && alarmHistoryDTO.getStatus() == true) {
			alarmHistoryDTO.setFinalDate(new Date());
			alarmHistoryDTO.setStatus(false);
			AlarmHistoryDTO dto = alarmsPresentService.save(alarmHistoryDTO).getDTO();
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

}
