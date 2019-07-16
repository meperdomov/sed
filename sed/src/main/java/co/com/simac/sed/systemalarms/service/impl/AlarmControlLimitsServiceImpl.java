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
import co.com.simac.sed.cube.dao.OrderIndexRepository;
import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.kpiindicatormanagement.dao.KPIIndicatorRepository;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.report.dao.AlarmHistoryRepository;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.model.KPIHistory;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.systemalarms.service.AlarmControlLimitsService;

@Service
public class AlarmControlLimitsServiceImpl implements
 AlarmControlLimitsService {

	@Autowired
	private KPIIndicatorRepository kpiIndicatorRepository;

	@Autowired
	private KPIHistoryRepository kpiHistoryRepository;

	@Autowired
	private AlarmHistoryRepository alarmHistoryRepository;

	@Autowired
	private OrderIndexRepository orderIndexRepository;

	@Autowired
	private KPIIndicatorAlarmRepository kpiIndicatorAlarmRepository;

	@Override
	// @Scheduled(initialDelay = 20000, fixedDelay = 100000)
	@Scheduled(cron = "0 0 22 * * ?")
	public void getAlarmsControlLimits() {
		Date initialDate = new Date();

		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(initialDate);
		gc1.set(Calendar.HOUR_OF_DAY, 21);
		gc1.set(Calendar.MINUTE, 0);
		gc1.set(Calendar.SECOND, 0);
		gc1.set(Calendar.MILLISECOND, 0);

		initialDate = gc1.getTime();

		ArrayList<KPIIndicator> kpiIndicators = (ArrayList<KPIIndicator>) kpiIndicatorRepository
				.findByActiveTrue();

		for (KPIIndicator kpiIndicator : kpiIndicators) {
			ArrayList<KPIHistory> kpiHistories = (ArrayList<KPIHistory>) kpiHistoryRepository
					.findKpiHistoryRecentByIndicator(kpiIndicator.getId());
			double deviationRef = 0;
			double mediaRef = 0;

			int inicial = 0;
			ArrayList<KPIIndicatorAlarm> kpiIndicatorAlarmList = (ArrayList<KPIIndicatorAlarm>) kpiIndicatorAlarmRepository
					.getKPIIndicatorAlarmByIndicatorId(kpiIndicator.getId());
			if (kpiIndicatorAlarmList.size() > 0) {
				for (KPIHistory kh : kpiHistories) {
					if (inicial == 0) {
						deviationRef = kh.getDeviation();
						mediaRef = kh.getAverageValue();

						ArrayList<OrderIndex> ordersindex = (ArrayList<OrderIndex>) orderIndexRepository
								.findOrdersIndexRecents(kh.getStartDate());
						inicial = inicial + 1;
						for (OrderIndex oi : ordersindex) {
							if (kh.isAuxiliaryService() == true) {
								float value = oi.getDirectIndex()
										+ oi.getIndirectIndex();
								for (KPIIndicatorAlarm ka : kpiIndicatorAlarmList) {
									if (ka.getAlarmType().getName()
											.equals("Límites de control")) {
										if ((value > (mediaRef + (ka
												.getFactor() * deviationRef)))
												|| (value < (mediaRef - (ka
														.getFactor() * deviationRef)))) {
											AlarmHistory alarmHistory = new AlarmHistory();

											alarmHistory.setDescription(ka
													.getMessage()
													+ " "
													+ kpiIndicator.getName());
											alarmHistory
													.setStartDate(new Date());
											alarmHistory
													.setFinalDate(new Date());
											alarmHistory.setStatus(true);
											alarmHistory.setPriority(ka
													.getPriority());
											alarmHistory
													.setKpiIndicatorAlarm(ka);
											alarmHistoryRepository
													.save(alarmHistory);
										}
										if ((value > (mediaRef + (ka
												.getFactor()
												* (ka.getPercentage() / 100) * deviationRef)))
												|| (value < (mediaRef - ((ka
														.getPercentage() / 100) * deviationRef)))) {
											AlarmHistory alarmHistory = new AlarmHistory();

											alarmHistory.setDescription(ka
													.getMessage()
													+ " "
													+ kpiIndicator.getName());
											alarmHistory
													.setStartDate(new Date());
											alarmHistory
													.setFinalDate(new Date());
											alarmHistory.setStatus(true);
											alarmHistory.setPriority(ka
													.getPriority());
											alarmHistory
													.setKpiIndicatorAlarm(ka);
											alarmHistoryRepository
													.save(alarmHistory);
										}
										if ((value > (mediaRef + ((ka
												.getPercentage() / 100) * deviationRef)))
												|| (value < (mediaRef - ((ka
														.getPercentage() / 100) * deviationRef)))) {
											AlarmHistory alarmHistory = new AlarmHistory();
											alarmHistory.setCause(ka
													.getPriority());
											alarmHistory.setDescription(ka
													.getMessage()
													+ " "
													+ kpiIndicator.getName());
											alarmHistory
													.setStartDate(new Date());
											alarmHistory
													.setFinalDate(new Date());
											alarmHistory.setStatus(true);
											alarmHistory.setPriority(ka
													.getPriority());

											alarmHistory
													.setKpiIndicatorAlarm(ka);
											alarmHistoryRepository
													.save(alarmHistory);
										}
									}
								}

							} else {
								float value = oi.getDirectIndex();
								for (KPIIndicatorAlarm ka : kpiIndicatorAlarmList) {
									ArrayList<AlarmHistory> alarmHistoryList = (ArrayList<AlarmHistory>) alarmHistoryRepository
											.getAlarmHistoryByKPIAlarmId(ka
													.getId());
									if (alarmHistoryList.size() == 0) {
										if (ka.getAlarmType().getName()
												.equals("Límites de control")) {
											if ((value > (mediaRef + (ka
													.getFactor() * deviationRef)))
													|| (value < (mediaRef - (ka
															.getFactor() * deviationRef)))) {
												AlarmHistory alarmHistory = new AlarmHistory();

												alarmHistory.setDescription(ka
														.getMessage()
														+ " "
														+ kpiIndicator
																.getName());
												alarmHistory
														.setStartDate(new Date());
												alarmHistory
														.setFinalDate(new Date());
												alarmHistory.setStatus(true);
												alarmHistory.setPriority(ka
														.getPriority());
												alarmHistory
														.setKpiIndicatorAlarm(ka);
												alarmHistoryRepository
														.save(alarmHistory);
											}
											if ((value > (mediaRef + (ka
													.getFactor()
													* (ka.getPercentage() / 100) * deviationRef)))
													|| (value < (mediaRef - (ka
															.getFactor()
															* (ka.getPercentage() / 100) * deviationRef)))) {
												AlarmHistory alarmHistory = new AlarmHistory();

												alarmHistory.setDescription(ka
														.getMessage()
														+ " "
														+ kpiIndicator
																.getName());
												alarmHistory
														.setStartDate(new Date());
												alarmHistory
														.setFinalDate(new Date());
												alarmHistory.setStatus(true);
												alarmHistory.setPriority(ka
														.getPriority());
												alarmHistory
														.setKpiIndicatorAlarm(ka);
												alarmHistoryRepository
														.save(alarmHistory);
											}
											if ((value > (mediaRef + ((ka
													.getPercentage() / 100) * deviationRef)))
													|| (value < (mediaRef - ((ka
															.getPercentage() / 100) * deviationRef)))) {
												AlarmHistory alarmHistory = new AlarmHistory();

												alarmHistory.setDescription(ka
														.getMessage()
														+ " "
														+ kpiIndicator
																.getName());
												alarmHistory
														.setStartDate(new Date());
												alarmHistory
														.setFinalDate(new Date());
												alarmHistory.setStatus(true);
												alarmHistory.setPriority(ka
														.getPriority());
												alarmHistory
														.setKpiIndicatorAlarm(ka);
												alarmHistoryRepository
														.save(alarmHistory);
											}
										}
									}
								}

							}
						}

					}

				}

			}
		}

	}
}