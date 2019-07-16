package co.com.simac.sed.report.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.report.service.AlarmHistoryService;
import co.com.simac.sed.systemalarms.dto.AlarmHistoryDTO;
import co.com.simac.sed.util.PageDTO;

@RestController
@RequestMapping("/report/alarmhistory")
public class AlarmHistoryController {

	@Autowired
	private AlarmHistoryService alarmHistoryService;

	@PreAuthorize("hasRole('ROLE_ALARMHISTORY_LIST')")
	@RequestMapping(method = RequestMethod.GET)
	public PageDTO<AlarmHistoryDTO> getAlarmHistoryDTOList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "idFailureCategory", defaultValue = "-1") long idFailureCategory,
			@RequestParam(value = "idKpiAlarm", defaultValue = "-1") long idKpiAlarm,
			@RequestParam(value = "initialDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date initialDate,
			@RequestParam(value = "finalDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date finalDate,
			@RequestParam(value = "status", defaultValue = "-1") int status) {
		return alarmHistoryService.getAlarmHistoryPageDTO(page, size, idFailureCategory, idKpiAlarm, initialDate,
				finalDate, status);
	}
}
