package co.com.simac.sed.systemalarms.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import co.com.simac.sed.alarmsmanagement.dao.KPIIndicatorAlarmRepository;
import co.com.simac.sed.alarmsmanagement.model.KPIIndicatorAlarm;
import co.com.simac.sed.kpiindicatormanagement.dao.KPIIndicatorRepository;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.report.dao.AlarmHistoryRepository;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.model.KPIHistory;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.systemalarms.service.AlarmKPIIndicatorVariationService;

@Service
public class AlarmKPIIndicatorVariationServiceImpl implements
		AlarmKPIIndicatorVariationService {

	@Autowired
	private KPIIndicatorRepository kpiIndicatorRepository;

	@Autowired
	private KPIHistoryRepository kpiHistoryRepository;

	@Autowired
	private AlarmHistoryRepository alarmHistoryRepository;

	@Autowired
	private KPIIndicatorAlarmRepository kpiIndicatorAlarmRepository;

	@Override
	// @Scheduled(initialDelay = 20000, fixedDelay = 100000)
	@Scheduled(cron = "0 0 20 15,30 * ?")
	public void getAlarmKPIIndicatorVariation() {
		Date initialDate = new Date();
		Date finalDate = new Date();

		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(initialDate);
		gc1.set(Calendar.HOUR_OF_DAY, 20);
		gc1.set(Calendar.MINUTE, 0);
		gc1.set(Calendar.SECOND, 0);
		gc1.set(Calendar.MILLISECOND, 0);
		gc1.set(Calendar.DAY_OF_MONTH, -14);
		initialDate = gc1.getTime();

		ArrayList<KPIIndicator> kpiIndicators = (ArrayList<KPIIndicator>) kpiIndicatorRepository
				.findByActiveTrue();

		for (KPIIndicator kpiIndicator : kpiIndicators) {
			ArrayList<KPIHistory> kpiHistories = (ArrayList<KPIHistory>) kpiHistoryRepository
					.findKpiHistoriesByKPiIndicator(initialDate, finalDate,
							kpiIndicator.getId());
			ArrayList<KPIIndicatorAlarm> kpiIndicatorAlarmList = (ArrayList<KPIIndicatorAlarm>) kpiIndicatorAlarmRepository
					.getKPIIndicatorAlarmByIndicatorId(kpiIndicator.getId());
			double totalValues = 0;
			double media = 0;
			double actualValue = 0;
			int inicial = 0;
			if (kpiIndicatorAlarmList.size() > 0) {
				for (KPIHistory kh : kpiHistories) {
					if (inicial == 0) {
						actualValue = kh.getAverageValue();
					} else {
						totalValues = totalValues + kh.getAverageValue();
					}
					inicial = inicial + 1;
				}
				media = totalValues / (kpiHistories.size() - 1);
				for (KPIIndicatorAlarm ka : kpiIndicatorAlarmList) {
					ArrayList<AlarmHistory> alarmHistoryList = (ArrayList<AlarmHistory>) alarmHistoryRepository
							.getAlarmHistoryByKPIAlarmId(ka.getId());
					if (alarmHistoryList.size() == 0) {
						if (ka.getAlarmType().getName()
								.equals("Variación del indicador")) {
							if (actualValue >= ((media * (ka.getPercentage() / 100)) + media)) {
								AlarmHistory alarmHistory = new AlarmHistory();

								alarmHistory
										.setDescription("Variacion del indicador > "
												+ ka.getPercentage()
												+ " % "
												+ kpiIndicator.getName());
								alarmHistory.setStartDate(new Date());
								alarmHistory.setFinalDate(new Date());
								alarmHistory.setStatus(true);
								alarmHistory.setPriority(ka.getPriority());
								alarmHistory.setKpiIndicatorAlarm(ka);
								alarmHistoryRepository.save(alarmHistory);
							}
						}
					}
				}
			}

		}

	}

}