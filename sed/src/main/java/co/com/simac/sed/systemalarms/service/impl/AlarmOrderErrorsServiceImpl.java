package co.com.simac.sed.systemalarms.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.dao.OrderRepository;
import co.com.simac.sed.cube.model.Order;
import co.com.simac.sed.enums.Parameter;
import co.com.simac.sed.general.dao.ParameterConfigurationRepository;
import co.com.simac.sed.general.model.ParameterConfiguration;
import co.com.simac.sed.report.dao.AlarmHistoryRepository;
import co.com.simac.sed.systemalarms.model.AlarmHistory;
import co.com.simac.sed.systemalarms.service.AlarmOrdersErrorService;

@Service
public class AlarmOrderErrorsServiceImpl implements AlarmOrdersErrorService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AlarmHistoryRepository alarmHistoryRepository;
	
	@Autowired
	private ParameterConfigurationRepository parameterConfigurationRepository;

	@Override
	// @Scheduled(initialDelay = 20000, fixedDelay = 100000)
	@Scheduled(cron = "0 0 13,20 * * *")
	public void getAlarmsOrdersError() {
		Date initialDate = new Date();
		Date finalDate = new Date();
		ParameterConfiguration pc=parameterConfigurationRepository.findByKey(Parameter.PORCENTAJE_ORDENES_FALLIDAS.toString());
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(initialDate);
		gc1.set(Calendar.HOUR_OF_DAY, -7);
		gc1.set(Calendar.MINUTE, 0);
		gc1.set(Calendar.SECOND, 0);
		gc1.set(Calendar.MILLISECOND, 0);
		initialDate = gc1.getTime();
		Integer totalLimitError=0;
		ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findOrdersByRange(initialDate, finalDate);
		Integer totalOrders = orders.size();
		if(pc!=null && !pc.getKey().equals("")){
			totalLimitError = (int) ((totalOrders * (Float.parseFloat(pc.getKey())/100)) + totalOrders);
		}
		else{
			totalLimitError = (int) ((totalOrders * 0.05) + totalOrders);
		}
		Integer totalOrdersError = 0;
		ArrayList<Long> ordersNumberError = new ArrayList<Long>();
		for (Order o : orders) {
			if (o.isCompletedWithError() == true) {
				totalOrdersError = totalOrdersError + 1;
				ordersNumberError.add(o.getOrderNumber());
			}

		}
		if (totalOrdersError >= totalLimitError) {
			AlarmHistory alarmHistory = new AlarmHistory();

			alarmHistory.setDescription("Ordenes finalizadas con error " + ordersNumberError.toString());
			alarmHistory.setStartDate(new Date());
			alarmHistory.setFinalDate(new Date());
			alarmHistory.setStatus(true);
			alarmHistoryRepository.save(alarmHistory);
		}

	}

}