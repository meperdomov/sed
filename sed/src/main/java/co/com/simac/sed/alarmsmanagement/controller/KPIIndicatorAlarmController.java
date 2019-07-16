package co.com.simac.sed.alarmsmanagement.controller;

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

import co.com.simac.sed.alarmsmanagement.dto.KPIIndicatorAlarmDTO;
import co.com.simac.sed.alarmsmanagement.service.KPIIndicatorAlarmService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/kpiindicatoralarm")
public class KPIIndicatorAlarmController {

	@Autowired
	private KPIIndicatorAlarmService kpiIndicatorAlarmService;

	@PreAuthorize("hasRole('ROLE_KPIINDICATOR_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<KPIIndicatorAlarmDTO> getKPIIndicatorAlarmDTOByAlarmType(
			@RequestParam("alarmTypeId") Long alarmTypeId, @RequestParam("kpiIndicatorId") Long kpiIndicatorId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		return kpiIndicatorAlarmService.getKPIIndicatorAlarmPageDTO(alarmTypeId, kpiIndicatorId, page, size);
	}

	@PreAuthorize("hasRole('ROLE_KPIINDICATOR_ALARM_GET')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<KPIIndicatorAlarmDTO> getKPIIndicatorAlarm(@PathVariable("id") long id) {
		KPIIndicatorAlarmDTO kpiIndicatorAlarmDTO = kpiIndicatorAlarmService.getKPIIndicatorAlarmById(id);
		if (kpiIndicatorAlarmDTO == null) {
			return new ResponseEntity<KPIIndicatorAlarmDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KPIIndicatorAlarmDTO>(kpiIndicatorAlarmDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_KPIINDICATOR_ALARM_SAVE')")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity save(@Valid @RequestBody KPIIndicatorAlarmDTO kpiIndicatorAlarmDTO, Errors errors) {

		if (!errors.hasErrors()) {
			KPIIndicatorAlarmDTO dto = kpiIndicatorAlarmService.save(kpiIndicatorAlarmDTO).getDTO();
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
	}

	@PreAuthorize("hasRole('ROLE_KPIINDICATOR_ALARM_DELETE')")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		kpiIndicatorAlarmService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}