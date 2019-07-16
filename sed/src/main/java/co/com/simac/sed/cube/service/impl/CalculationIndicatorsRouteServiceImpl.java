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
import co.com.simac.sed.cube.dao.OrderIndexRepository;
import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.cube.service.CalculationIndicatorsRouteService;
import co.com.simac.sed.kpiindicatormanagement.dao.KPIIndicatorRepository;
import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.report.dao.AlarmHistoryRepository;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.model.KPIHistory;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.util.Statistics;

@Service
public class CalculationIndicatorsRouteServiceImpl implements
		CalculationIndicatorsRouteService {

	@Autowired
	private KPIIndicatorRepository kpiindicatorRepository;

	@Autowired
	private KPIHistoryRepository kpiHistoryRepository;

	@Autowired
	private OrderIndexRepository orderConsumptionRepository;

	@Autowired
	private KPIIndicatorAlarmRepository kpiIndicatorAlarmRepository;

	@Autowired
	private AlarmHistoryRepository alarmHistoryRepository;

	// @Scheduled(initialDelay = 20000, fixedDelay = 100000)
	@Override
	public void calculateIndicatorsRoute() {
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

		// //Fechas de Prueba
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String starDateTest = "2016-04-25 07:00:00";
		// String endDateTest = "2016-04-27 03:00:00";
		// try {
		// initialDate=sdf.parse(starDateTest);
		// finaleDate =sdf.parse(endDateTest);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }

		ArrayList<KPIIndicator> indicatorsByRoute = (ArrayList<KPIIndicator>) kpiindicatorRepository
				.findByRouteNotNull();

		for (KPIIndicator kpiindicator : indicatorsByRoute) {
			Float indexOrders = (float) 0;
			Float indexOrdersAuxiliary = (float) 0;
			ArrayList<OrderIndex> ordersConsumption = (ArrayList<OrderIndex>) orderConsumptionRepository
					.getOrderConsumptionByRange(initialDate, finalDate,
							kpiindicator.getVariable().getId(), kpiindicator
									.getRoute().getId());

			double[] xCoordinate = new double[ordersConsumption.size()];
			double[] xCoordinateAuxiliary = new double[ordersConsumption.size()];
			double[] yCoordinate = new double[ordersConsumption.size()];
			int iterator = 0;
			double coefficient = -1;
			double coefficientAuxiliary = -1;
			float valueKpi = 0;
			float valueKpiAuxiliary = 0;
			double deviation = 0;
			double deviationAuxiliary = 0;
			double intercept = 0;
			double interceptAuxiliary = 0;
			double slope = 0;
			double slopeAuxiliary = 0;
			ArrayList<Float> indexList = new ArrayList<Float>();
			ArrayList<Float> indexListAux = new ArrayList<Float>();
			for (OrderIndex orderConsumption : ordersConsumption) {

				indexOrders = indexOrders + orderConsumption.getDirectIndex();
				indexOrdersAuxiliary = indexOrdersAuxiliary
						+ orderConsumption.getIndirectIndex();
				indexList.add(orderConsumption.getDirectIndex());
				xCoordinate[iterator] = orderConsumption.getDirectConsumption();
				xCoordinateAuxiliary[iterator] = orderConsumption
						.getDirectConsumption()
						+ orderConsumption.getIndirectConsumption();
				yCoordinate[iterator] = orderConsumption.getQuantity();
				iterator++;
			}

			if (indexOrders > 0 && ordersConsumption.size() > 0) {
				deviation = Statistics.standardDeviation(indexList);
				coefficient = this.CalculatePearsonsCoefficient(xCoordinate,
						yCoordinate);
				SimpleRegression regression = Statistics
						.createLinearRegression(xCoordinate, yCoordinate);
				intercept = regression.getIntercept();
				slope = regression.getSlope();
				valueKpi = indexOrders / ordersConsumption.size();

			}

			if (indexOrdersAuxiliary > 0 && ordersConsumption.size() > 0) {
				deviationAuxiliary = Statistics.standardDeviation(indexListAux);
				coefficientAuxiliary = this.CalculatePearsonsCoefficient(
						xCoordinateAuxiliary, yCoordinate);
				SimpleRegression regression = Statistics
						.createLinearRegression(xCoordinateAuxiliary,
								yCoordinate);
				interceptAuxiliary = regression.getIntercept();
				slopeAuxiliary = regression.getSlope();
				valueKpiAuxiliary = indexOrdersAuxiliary
						/ ordersConsumption.size();

			}
			kpiindicatorRepository.save(kpiindicator);

			CreateKPIHistory(kpiindicator, initialDate, finalDate,
					ordersConsumption, coefficient, valueKpi, deviation,
					intercept, slope, false);

			CreateKPIHistory(kpiindicator, initialDate, finalDate,
					ordersConsumption, coefficientAuxiliary, valueKpiAuxiliary,
					deviationAuxiliary, interceptAuxiliary, slopeAuxiliary,
					true);

		}

	}

	@Override
	public void calculateIndicatorsRouteMaterial() {
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

		ArrayList<KPIIndicator> indicatorsByRouteAndMaterial = (ArrayList<KPIIndicator>) kpiindicatorRepository
				.findByRouteAndMaterialNotNull();

		for (KPIIndicator kpiindicator : indicatorsByRouteAndMaterial) {
			Float indexOrders = (float) 0;

			ArrayList<OrderIndex> orderByMaterial = (ArrayList<OrderIndex>) orderConsumptionRepository
					.getOrderConsumptionByRouteMaterial(initialDate, finalDate,
							kpiindicator.getVariable().getId(), kpiindicator
									.getRoute().getId(), kpiindicator
									.getMaterial().getId());
			double[] xCoordinate = new double[orderByMaterial.size()];
			double[] yCoordinate = new double[orderByMaterial.size()];
			int iterator = 0;
			double coefficient = -1;
			float valueKpi = 0;
			double deviation = 0;
			double intercept = 0;
			double slope = 0;
			ArrayList<Float> indexList = new ArrayList<Float>();
			for (OrderIndex oc : orderByMaterial) {
				indexOrders = indexOrders + oc.getDirectIndex();

				indexList.add(oc.getDirectIndex());
				xCoordinate[iterator] = oc.getDirectConsumption();
				yCoordinate[iterator] = oc.getQuantity();
				iterator++;
			}
			if (indexOrders > 0 && orderByMaterial.size() > 0) {
				coefficient = this.CalculatePearsonsCoefficient(xCoordinate,
						yCoordinate);
				deviation = Statistics.standardDeviation(indexList);
				SimpleRegression regression = Statistics
						.createLinearRegression(xCoordinate, yCoordinate);
				intercept = regression.getIntercept();
				slope = regression.getSlope();
				valueKpi = indexOrders / orderByMaterial.size();

			}
			kpiindicatorRepository.save(kpiindicator);
			CreateKPIHistory(kpiindicator, initialDate, finalDate,
					orderByMaterial, coefficient, valueKpi, deviation,
					intercept, slope, false);

		}

	}

	private void CreateKPIHistory(KPIIndicator kpiindicator, Date startDate,
			Date endDate, ArrayList<OrderIndex> ordersIndex,
			double coefficient, float averageValueKpi, double deviation,
			double intercept, double slope, boolean isAuxiliary) {
		KPIHistory kpiHistory = new KPIHistory();
		kpiHistory.setKpiindicator(kpiindicator);
		kpiHistory.setAverageValue(averageValueKpi);
		kpiHistory.setStartDate(startDate);

		kpiHistory.setCorrelation(coefficient);
		kpiHistory.setDeviation(deviation);
		kpiHistory.setIntercept(intercept);
		kpiHistory.setSlope(slope);
		kpiHistory.setEndDate(endDate);
		kpiHistory.setOrdersIndex(ordersIndex);
		kpiHistory.setAuxiliaryService(isAuxiliary);

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

				}
			}
		}
		kpiHistoryRepository.save(kpiHistory);
	}

	private double CalculatePearsonsCoefficient(double[] xArray, double[] yArray) {
		return Statistics.correlationPearson(xArray, yArray);
	}

}
