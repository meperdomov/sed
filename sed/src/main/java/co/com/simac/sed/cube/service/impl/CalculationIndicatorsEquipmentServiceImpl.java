package co.com.simac.sed.cube.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.alarmsmanagement.dao.KPIIndicatorAlarmRepository;
import co.com.simac.sed.alarmsmanagement.model.KPIIndicatorAlarm;
import co.com.simac.sed.cube.dao.ActivityConsumptionRepository;
import co.com.simac.sed.cube.dao.OrderIndexRepository;
import co.com.simac.sed.cube.model.ActivityConsumption;
import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.cube.service.CalculationIndicatorsEquipmentService;
import co.com.simac.sed.kpiindicatormanagement.dao.KPIIndicatorRepository;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.report.dao.AlarmHistoryRepository;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.model.KPIHistory;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.util.Statistics;

@Service
public class CalculationIndicatorsEquipmentServiceImpl implements
		CalculationIndicatorsEquipmentService {

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
			Date endDate, ArrayList<OrderIndex> equipmentsIndex,
			double coefficient, float averageValueKpi, double deviation,
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
		kpiHistory.setOrdersIndex(equipmentsIndex);
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
	public void calculateIndexEquipment() {
		Date initialDate = new Date();
		Date finalDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(initialDate);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.DAY_OF_YEAR, -30);
		initialDate = gc.getTime();

		ArrayList<KPIIndicator> indicatorsByEquipment = (ArrayList<KPIIndicator>) kpiindicatorRepository
				.findByEquipmentNotNull();

		for (KPIIndicator kpiindicator : indicatorsByEquipment) {

			ArrayList<ActivityConsumption> activities = (ArrayList<ActivityConsumption>) activityConsumptionRepository
					.getActivitiesByEquipment(kpiindicator.getEquipment()
							.getId(), initialDate, finalDate, kpiindicator
							.getVariable().getId());

			for (ActivityConsumption activity : activities) {
				ArrayList<OrderIndex> equipmentIndexes = (ArrayList<OrderIndex>) orderIndexRepository
						.getEquipmentIndexByRange(initialDate, finalDate,
								activity.getVariable().getId(), kpiindicator
										.getEquipment().getId(), activity
										.getActivity().getOrder().getId());
				if (equipmentIndexes.size() > 0) {
					for (OrderIndex equipmentIndex : equipmentIndexes) {
						equipmentIndex.setDirectConsumption(equipmentIndex
								.getDirectConsumption()
								+ activity.getConsumption());
						if (activity.getActivity().getOrder().getQuantity() > 0) {
							equipmentIndex.setDirectIndex(equipmentIndex
									.getDirectConsumption()
									/ activity.getActivity().getOrder()
											.getQuantity());
						} else {
							equipmentIndex.setDirectIndex((float) 0);
						}
						orderIndexRepository.save(equipmentIndex);
					}
				} else {
					OrderIndex equipmentIndex = new OrderIndex();
					equipmentIndex.setDirectConsumption(activity
							.getConsumption());
					equipmentIndex.setQuantity(activity.getActivity()
							.getOrder().getQuantity());
					equipmentIndex.setOrder(activity.getActivity().getOrder());
					equipmentIndex.setVariable(activity.getVariable());
					equipmentIndex.setUnitMeasure(activity.getUnitMeasure());
					equipmentIndex.setEquipment(kpiindicator.getEquipment());
					if (activity.getActivity().getOrder().getQuantity() > 0) {
						equipmentIndex.setDirectIndex(equipmentIndex
								.getDirectConsumption()
								/ activity.getActivity().getOrder()
										.getQuantity());
					} else {
						equipmentIndex.setDirectIndex((float) 0);
					}

					orderIndexRepository.save(equipmentIndex);
				}

			}

		}

	}

	@Override
	// @Scheduled(initialDelay = 70000, fixedDelay = 100000)
	public void calculateIndicatorsEquipment() {
		Date initialDate = new Date();
		Date finalDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(initialDate);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.DAY_OF_YEAR, -30);
		initialDate = gc.getTime();

		ArrayList<KPIIndicator> indicatorsByEquipment = (ArrayList<KPIIndicator>) kpiindicatorRepository
				.findByEquipmentNotNull();

		for (KPIIndicator kpiindicator : indicatorsByEquipment) {

			Float indexEquipments = (float) 0;

			ArrayList<OrderIndex> equipmentIndexes = (ArrayList<OrderIndex>) orderIndexRepository
					.getEquipmentIndexByRange(initialDate, finalDate,
							kpiindicator.getVariable().getId(), kpiindicator
									.getEquipment().getId());
			double[] xCoordinate = new double[equipmentIndexes.size()];
			double[] yCoordinate = new double[equipmentIndexes.size()];
			int iterator = 0;
			float value = 0;
			double deviation = 0;
			ArrayList<Float> indexList = new ArrayList<Float>();
			double coefficient = -1;
			double intercept = 0;
			double slope = 0;
			for (OrderIndex equipmentIndex : equipmentIndexes) {
				indexEquipments = indexEquipments
						+ equipmentIndex.getDirectIndex();

				indexList.add(equipmentIndex.getDirectIndex());
				xCoordinate[iterator] = equipmentIndex.getDirectConsumption();
				yCoordinate[iterator] = equipmentIndex.getQuantity();
				iterator++;
			}
			if (equipmentIndexes.size() > 0) {
				coefficient = this.CalculatePearsonsCoefficient(xCoordinate,
						yCoordinate);
				deviation = Statistics.standardDeviation(indexList);
				SimpleRegression regression = Statistics
						.createLinearRegression(xCoordinate, yCoordinate);
				intercept = regression.getIntercept();
				slope = regression.getSlope();
				value = indexEquipments / equipmentIndexes.size();
			}
			kpiindicatorRepository.save(kpiindicator);

			CreateKPIHistory(kpiindicator, initialDate, finalDate,
					equipmentIndexes, coefficient, value, deviation, intercept,
					slope);

		}

	}

	@Override
	// @Scheduled(initialDelay = 40000, fixedDelay = 100000)
	public void calculateIndexEquipmentMaterial() {
		Date initialDate = new Date();
		Date finalDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(initialDate);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.DAY_OF_YEAR, -30);
		initialDate = gc.getTime();

		ArrayList<KPIIndicator> indicatorsByEquipment = (ArrayList<KPIIndicator>) kpiindicatorRepository
				.findByEquipmentAndMaterialNotNull();

		for (KPIIndicator kpiindicator : indicatorsByEquipment) {

			ArrayList<ActivityConsumption> activities = (ArrayList<ActivityConsumption>) activityConsumptionRepository
					.getActivitiesByEquipmentMaterial(kpiindicator
							.getEquipment().getId(), kpiindicator.getMaterial()
							.getCode(), initialDate, finalDate, kpiindicator
							.getVariable().getId());

			for (ActivityConsumption activity : activities) {
				ArrayList<OrderIndex> equipmentIndexes = (ArrayList<OrderIndex>) orderIndexRepository
						.getEquipmentIndexByMaterial(initialDate, finalDate,
								activity.getVariable().getId(), kpiindicator
										.getEquipment().getId(), kpiindicator
										.getMaterial().getId());

				if (equipmentIndexes.size() > 0) {
					for (OrderIndex ecupdate : equipmentIndexes) {
						ecupdate.setDirectConsumption(ecupdate
								.getDirectConsumption()
								+ activity.getConsumption());
						if (activity.getActivity().getOrder().getQuantity() > 0) {
							ecupdate.setDirectIndex(ecupdate
									.getDirectConsumption()
									/ activity.getActivity().getOrder()
											.getQuantity());
						} else {
							ecupdate.setDirectIndex((float) 0);
						}
						orderIndexRepository.save(ecupdate);
					}
				} else {
					OrderIndex equipmentIndex = new OrderIndex();
					equipmentIndex.setDirectConsumption(activity
							.getConsumption());
					equipmentIndex.setOrder(activity.getActivity().getOrder());
					equipmentIndex.setQuantity(activity.getActivity()
							.getOrder().getQuantity());
					equipmentIndex.setVariable(activity.getVariable());
					equipmentIndex.setUnitMeasure(activity.getUnitMeasure());
					equipmentIndex.setEquipment(kpiindicator.getEquipment());
					equipmentIndex.setMaterial(kpiindicator.getMaterial());
					if (activity.getActivity().getOrder().getQuantity() > 0) {
						equipmentIndex.setDirectIndex(equipmentIndex
								.getDirectConsumption()
								/ activity.getActivity().getOrder()
										.getQuantity());
					} else {
						equipmentIndex.setDirectIndex((float) 0);
					}

					orderIndexRepository.save(equipmentIndex);
				}

			}

		}

	}

	@Override
	// @Scheduled(initialDelay = 95000, fixedDelay = 100000)
	public void calculateIndicatorsEquipmentMaterial() {
		Date initialDate = new Date();
		Date finalDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(initialDate);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.DAY_OF_YEAR, -30);
		initialDate = gc.getTime();

		ArrayList<KPIIndicator> indicatorsByEquipment = (ArrayList<KPIIndicator>) kpiindicatorRepository
				.findByEquipmentAndMaterialNotNull();

		for (KPIIndicator kpiindicator : indicatorsByEquipment) {

			Float indexEquipments = (float) 0;

			ArrayList<OrderIndex> ec = (ArrayList<OrderIndex>) orderIndexRepository
					.getEquipmentIndexByMaterial(initialDate, finalDate,
							kpiindicator.getVariable().getId(), kpiindicator
									.getEquipment().getId(), kpiindicator
									.getMaterial().getId());
			double[] xCoordinate = new double[ec.size()];
			double[] yCoordinate = new double[ec.size()];
			int iterator = 0;
			double coefficient = -1;
			float valueKpi = 0;
			double deviation = 0;
			double intercept = 0;
			double slope = 0;
			ArrayList<Float> indexList = new ArrayList<Float>();
			for (OrderIndex ecupdate : ec) {
				indexEquipments = indexEquipments + ecupdate.getDirectIndex();

				indexList.add(ecupdate.getDirectIndex());
				xCoordinate[iterator] = ecupdate.getDirectConsumption();
				yCoordinate[iterator] = ecupdate.getQuantity();
				iterator++;

			}
			if (ec.size() > 0) {
				deviation = Statistics.standardDeviation(indexList);
				coefficient = this.CalculatePearsonsCoefficient(xCoordinate,
						yCoordinate);
				SimpleRegression regression = Statistics
						.createLinearRegression(xCoordinate, yCoordinate);
				intercept = regression.getIntercept();
				slope = regression.getSlope();
				valueKpi = indexEquipments / ec.size();

			}
			kpiindicatorRepository.save(kpiindicator);
			CreateKPIHistory(kpiindicator, initialDate, finalDate, ec,
					coefficient, valueKpi, deviation, intercept, slope);
		}
	}

	private double CalculatePearsonsCoefficient(double[] xArray, double[] yArray) {
		return Statistics.correlationPearson(xArray, yArray);
	}

}
