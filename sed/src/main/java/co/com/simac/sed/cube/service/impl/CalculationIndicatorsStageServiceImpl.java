package co.com.simac.sed.cube.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import co.com.simac.sed.alarmsmanagement.dao.KPIIndicatorAlarmRepository;
import co.com.simac.sed.alarmsmanagement.model.KPIIndicatorAlarm;
import co.com.simac.sed.cube.dao.ActivityConsumptionRepository;
import co.com.simac.sed.cube.dao.OrderIndexRepository;
import co.com.simac.sed.cube.model.ActivityConsumption;
import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.cube.service.CalculationIndicatorsStageService;
import co.com.simac.sed.kpiindicatormanagement.dao.KPIIndicatorRepository;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.report.dao.AlarmHistoryRepository;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.model.KPIHistory;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.util.Statistics;

@Service
public class CalculationIndicatorsStageServiceImpl implements
		CalculationIndicatorsStageService {

	@Autowired
	private ActivityConsumptionRepository activityConsumptionRepository;

	@Autowired
	private KPIIndicatorRepository kpiindicatorRepository;

	@Autowired
	private KPIHistoryRepository kpiHistoryRepository;

	@Autowired
	private OrderIndexRepository orderIndexRepository;

	@Autowired
	private KPIIndicatorAlarmRepository kpiIndicatorAlarmRepository;

	@Autowired
	private AlarmHistoryRepository alarmHistoryRepository;

	private void CreateKPIHistory(KPIIndicator kpiindicator, Date startDate,
			Date endDate, ArrayList<OrderIndex> ordersIndex,
			double coefficient, double averageValueKpi, double deviation,
			double intercept, double slope) {
		KPIHistory kpiHistory = new KPIHistory();
		kpiHistory.setKpiindicator(kpiindicator);
		kpiHistory.setAverageValue(averageValueKpi);
		kpiHistory.setStartDate(startDate);
		kpiHistory.setEndDate(endDate);
		kpiHistory.setCorrelation(coefficient);
		kpiHistory.setDeviation(deviation);
		kpiHistory.setIntercept(intercept);
		kpiHistory.setSlope(slope);
		kpiHistory.setOrdersIndex(ordersIndex);
		kpiHistory.setUtil(true);

		ArrayList<KPIIndicatorAlarm> kpiIndicatorAlarms = (ArrayList<KPIIndicatorAlarm>) kpiIndicatorAlarmRepository
				.getKPIAlarmByKPIandCorrelacion(kpiindicator.getId());
		if (kpiIndicatorAlarms.size() > 0) {
			KPIIndicatorAlarm kpiAlarm = kpiIndicatorAlarms.get(0);
			ArrayList<AlarmHistory> alarmHistoryList = (ArrayList<AlarmHistory>) alarmHistoryRepository
					.getAlarmHistoryByKPIAlarmId(kpiAlarm.getId());
			if (alarmHistoryList.size() == 0) {
				if (coefficient < kpiAlarm.getCorrelation()) {
					AlarmHistory alarmHistory = new AlarmHistory();

					alarmHistory.setDescription("No alcanza correlacion > "
							+ kpiAlarm.getCorrelation() + " "
							+ kpiindicator.getName());
					alarmHistory.setStartDate(new Date());
					alarmHistory.setFinalDate(new Date());
					alarmHistory.setStatus(true);
					alarmHistory.setPriority(kpiAlarm.getPriority());
					alarmHistory.setKpiIndicatorAlarm(kpiAlarm);
					alarmHistoryRepository.save(alarmHistory);
					kpiHistory.setUtil(false);
				}
			}
		}
		kpiHistoryRepository.save(kpiHistory);

	}

	@Override
	// @Scheduled(initialDelay = 20000, fixedDelay = 100000)
	@Scheduled(cron = "0 0 20 15,30 * ?")
	public void calculateIndexStage() {
		Date initialDate = new Date();
		Date finalDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(initialDate);
		gc.set(Calendar.HOUR_OF_DAY, 19);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.DAY_OF_MONTH, -14);
		initialDate = gc.getTime();

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String starDateTest = "2017-04-25 00:00:00";
		// String endDateTest = "2017-04-28 23:00:00";
		// try {
		// initialDate=sdf.parse(starDateTest);
		// finalDate =sdf.parse(endDateTest);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// System.out.println("fecha inicio " + initialDate.toString());
		// System.out.println("fecha fin " + finalDate.toString());

		ArrayList<KPIIndicator> indicatorsByStage = (ArrayList<KPIIndicator>) kpiindicatorRepository
				.findByStageNotNull();

		for (KPIIndicator kpiindicator : indicatorsByStage) {

			ArrayList<ActivityConsumption> activities = (ArrayList<ActivityConsumption>) activityConsumptionRepository
					.getActivitiesByStage(kpiindicator.getStage(), initialDate,
							finalDate, kpiindicator.getVariable().getId());

			for (ActivityConsumption activity : activities) {
				ArrayList<OrderIndex> orderIndexes = (ArrayList<OrderIndex>) orderIndexRepository
						.getStageIndexByRange(initialDate, finalDate, activity
								.getVariable().getId(),
								kpiindicator.getStage(), activity.getActivity()
										.getOrder().getId());
				if (orderIndexes.size() > 0) {
					for (OrderIndex orderIndex : orderIndexes) {
						orderIndex.setDirectConsumption(orderIndex
								.getDirectConsumption()
								+ activity.getConsumption());
						if (activity.getActivity().getOrder().getQuantity() > 0) {
							orderIndex.setDirectIndex(orderIndex
									.getDirectConsumption()
									/ activity.getActivity().getOrder()
											.getQuantity());
						} else {
							orderIndex.setDirectIndex((float) 0);
						}
						orderIndexRepository.save(orderIndex);
					}
				} else {
					OrderIndex orderIndex = new OrderIndex();
					orderIndex.setDirectConsumption(activity.getConsumption());
					orderIndex.setQuantity(activity.getActivity().getOrder()
							.getQuantity());
					orderIndex.setOrder(activity.getActivity().getOrder());
					orderIndex.setVariable(activity.getVariable());
					orderIndex.setUnitMeasure(activity.getUnitMeasure());
					orderIndex.setStage(kpiindicator.getStage());
					if (activity.getActivity().getOrder().getQuantity() > 0) {
						orderIndex.setDirectIndex(orderIndex
								.getDirectConsumption()
								/ activity.getActivity().getOrder()
										.getQuantity());
					} else {
						orderIndex.setDirectIndex((float) 0);
					}

					orderIndexRepository.save(orderIndex);
				}

			}

		}

	}

	@Override
	// @Scheduled(initialDelay = 20000, fixedDelay = 100000)
	@Scheduled(cron = "0 0 20 15,30 * ?")
	public void calculateIndicatorsStage() {
		Date initialDate = new Date();
		Date finalDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(initialDate);
		gc.set(Calendar.HOUR_OF_DAY, 19);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.DAY_OF_MONTH, -14);
		initialDate = gc.getTime();

		ArrayList<KPIIndicator> indicatorsByStage = (ArrayList<KPIIndicator>) kpiindicatorRepository
				.findByStageNotNull();

		for (KPIIndicator kpiindicator : indicatorsByStage) {

			Float indexStages = (float) 0;

			ArrayList<OrderIndex> orderIndexes = (ArrayList<OrderIndex>) orderIndexRepository
					.getStageIndexByRange(initialDate, finalDate, kpiindicator
							.getVariable().getId(), kpiindicator.getStage());
			double[] xCoordinate = new double[orderIndexes.size()];
			double[] yCoordinate = new double[orderIndexes.size()];
			int iterator = 0;
			float value = 0;
			double deviation = 0;
			ArrayList<Float> indexList = new ArrayList<Float>();
			double coefficient = -1;
			double intercept = 0;
			double slope = 0;

			for (OrderIndex orderIndex : orderIndexes) {
				indexStages = indexStages + orderIndex.getDirectIndex();

				indexList.add(orderIndex.getDirectIndex());
				xCoordinate[iterator] = orderIndex.getDirectConsumption();
				yCoordinate[iterator] = orderIndex.getQuantity();
				iterator++;
			}
			if (orderIndexes.size() > 0) {
				coefficient = this.CalculatePearsonsCoefficient(xCoordinate,
						yCoordinate);
				deviation = Statistics.standardDeviation(indexList);
				SimpleRegression regression = Statistics
						.createLinearRegression(xCoordinate, yCoordinate);
				intercept = regression.getIntercept();
				slope = regression.getSlope();
				value = (indexStages / orderIndexes.size());
			}
			kpiindicatorRepository.save(kpiindicator);

			CreateKPIHistory(kpiindicator, initialDate, finalDate,
					orderIndexes, coefficient, value, deviation, intercept,
					slope);

		}

	}

	private double CalculatePearsonsCoefficient(double[] xArray, double[] yArray) {
		return Statistics.correlationPearson(xArray, yArray);
	}

}
