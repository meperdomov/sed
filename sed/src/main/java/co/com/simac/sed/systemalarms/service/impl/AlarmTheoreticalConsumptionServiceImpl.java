package co.com.simac.sed.systemalarms.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.model.MeasurerConsumption;
import co.com.simac.sed.report.dao.AlarmHistoryRepository;
import co.com.simac.sed.report.dao.MeasurerConsumptionRepository;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.systemalarms.service.AlarmTheoreticalConsumptionService;

@Service
public class AlarmTheoreticalConsumptionServiceImpl implements AlarmTheoreticalConsumptionService {

	@Autowired
	private MeasurerConsumptionRepository measurerConsumptionRepository;

	@Autowired
	private AlarmHistoryRepository alarmHistoryRepository;

	@Override
	// @Scheduled(initialDelay = 20000, fixedDelay = 100000)
	@Scheduled(cron = "0 0 13,20 * * *")
	public void getAlarmsConsumptionTheorical() {
		Date initialDate = new Date();
		Date finalDate = new Date();

		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(initialDate);
		gc1.set(Calendar.HOUR_OF_DAY, -7);
		gc1.set(Calendar.MINUTE, 0);
		gc1.set(Calendar.SECOND, 0);
		gc1.set(Calendar.MILLISECOND, 0);
		initialDate = gc1.getTime();

		ArrayList<MeasurerConsumption> measurersConsumption = (ArrayList<MeasurerConsumption>) measurerConsumptionRepository
				.findMeasurerConsumptionRange(initialDate, finalDate);

		for (MeasurerConsumption mc : measurersConsumption) {
			float consumption = mc.getConsumption();

			float theoricConsumption = mc.getMeasurer().getReferenceConsumption();
			float limit = (float) ((theoricConsumption * 0.10) + theoricConsumption);
			if (consumption >= limit) {
				AlarmHistory alarmHistory = new AlarmHistory();
				alarmHistory.setCause("Supera consumo teorico");
				alarmHistory.setDescription("Supera consumo teorico del medidor " + mc.getMeasurer().getName());
				alarmHistory.setStartDate(new Date());
				alarmHistory.setFinalDate(new Date());
				alarmHistory.setStatus(true);
				alarmHistoryRepository.save(alarmHistory);
			}
		}

	}

}