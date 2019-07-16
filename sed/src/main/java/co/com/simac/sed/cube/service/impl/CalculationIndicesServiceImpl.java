package co.com.simac.sed.cube.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import co.com.simac.sed.auxiliaryservicesmanagement.dao.AuxiliaryServiceConfigurationRepository;
import co.com.simac.sed.auxiliaryservicesmanagement.model.AuxiliaryServiceConfiguration;
import co.com.simac.sed.cube.dao.ActivityConsumptionRepository;
import co.com.simac.sed.cube.dao.OrderIndexRepository;
import co.com.simac.sed.cube.dao.OrderRepository;
import co.com.simac.sed.cube.model.ActivityConsumption;
import co.com.simac.sed.cube.model.MeasurerConsumption;
import co.com.simac.sed.cube.model.Order;
import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.cube.service.CalculationIndicesService;
import co.com.simac.sed.kpiindicatormanagement.dao.VariableRepository;
import co.com.simac.sed.kpiindicatormanagement.model.Variable;
import co.com.simac.sed.report.dao.MeasurerConsumptionRepository;

@Service
public class CalculationIndicesServiceImpl implements CalculationIndicesService {

	@Autowired
	private ActivityConsumptionRepository activityConsumptionRepository;

	@Autowired
	private OrderIndexRepository orderConsumptionRepository;

	@Autowired
	private OrderRepository orderRepository;

	//@Autowired
	//private ActivityRepository activityRepository;

	@Autowired
	private VariableRepository variableRepository;

	@Autowired
	private MeasurerConsumptionRepository measurerConsumptionRepository;

	@Autowired
	private AuxiliaryServiceConfigurationRepository auxServiceConfigRepository;

	@Override
	// @Scheduled(initialDelay = 20000, fixedDelay = 100000)
	@Scheduled(cron = "0 0 21 * * ?")
	public void getTodayActivities() {
		Date todayDate = new Date();
		Date finalDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(todayDate);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		todayDate = gc.getTime();

		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(finalDate);
		gc1.set(Calendar.HOUR_OF_DAY, 20);
		gc1.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc1.set(Calendar.MILLISECOND, 0);
		finalDate = gc1.getTime();


		ArrayList<Variable> variables = (ArrayList<Variable>) variableRepository
				.findAll();

		for (Variable variable : variables) {
			ArrayList<ActivityConsumption> activities = (ArrayList<ActivityConsumption>) activityConsumptionRepository
					.getTodayActivities(todayDate, finalDate, variable.getId());

			for (ActivityConsumption activity : activities) {

				Order order = activity.getActivity().getOrder();

				if (order != null && order.isCompletedWithError()==false) {

					ArrayList<OrderIndex> orderIndexes = (ArrayList<OrderIndex>) orderConsumptionRepository
							.getOrderByVariable(order.getId(), variable.getId());
					if (orderIndexes.size() > 0) {
						for (OrderIndex orderIndex : orderIndexes) {
							orderIndex.setDirectConsumption(orderIndex
									.getDirectConsumption()
									+ activity.getConsumption());
							if (order.getQuantity() > 0) {
								orderIndex.setDirectIndex(orderIndex.getDirectConsumption()
										/ order.getQuantity());
							} else {
								orderIndex.setDirectIndex((float) 0);
							}
							orderConsumptionRepository.save(orderIndex);
						}
					} else {
						OrderIndex orderIndex = new OrderIndex();
						orderIndex.setDirectConsumption(activity.getConsumption());
						orderIndex.setOrder(order);
						orderIndex.setVariable(variable);
						orderIndex.setQuantity(order.getQuantity());
						orderIndex.setUnitMeasure(activity.getUnitMeasure());
						
						if (order.getQuantity() > 0) {
							orderIndex.setDirectIndex(orderIndex.getDirectConsumption()
									/ order.getQuantity());
						} else {
							orderIndex.setDirectIndex((float) 0);
						}
						orderConsumptionRepository.save(orderIndex);
					}
				}
			}
			calculateIndirectConsumption(todayDate,finalDate,variable.getId());
		}

	}

	private void calculateIndirectConsumption(Date todayDate,Date finalDate,Long variableId) {
		
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.setTime(todayDate);
//		gc.set(Calendar.HOUR_OF_DAY, 0);
//		gc.set(Calendar.MINUTE, 0);
//		gc.set(Calendar.SECOND, 0);
//		gc.set(Calendar.MILLISECOND, 0);
//		todayDate = gc.getTime();
//
//		GregorianCalendar gc1 = new GregorianCalendar();
//		gc1.setTime(finalDate);
//		gc1.set(Calendar.HOUR_OF_DAY, 20);
//		gc1.set(Calendar.MINUTE, 0);
//		gc.set(Calendar.SECOND, 0);
//		gc1.set(Calendar.MILLISECOND, 0);
//		finalDate = gc1.getTime();

		ArrayList<Order> orders = (ArrayList<Order>) orderRepository
				.findOrdersByRange(todayDate, finalDate);
		ArrayList<MeasurerConsumption> measurerConsumptions = (ArrayList<MeasurerConsumption>) measurerConsumptionRepository
				.findMeasurerConsumptionIndirect(todayDate, finalDate,variableId);
		ArrayList<AuxiliaryServiceConfiguration> auxServiceConfigProvide = (ArrayList<AuxiliaryServiceConfiguration>) auxServiceConfigRepository
				.getAuxiliaryServiceConfigByProvideEquipments();
		HashMap<Long,Double> consumptionEquipment = new HashMap<Long,Double>();
		for (AuxiliaryServiceConfiguration asc : auxServiceConfigProvide) {
			  double consumption=0;
			for (MeasurerConsumption ms : measurerConsumptions) {
				if (ms.getMeasurer().getEquipment().getId() == asc
						.getEquipment().getId()) {
						if(consumptionEquipment.containsKey(ms.getMeasurer().getEquipment().getId())){
							consumption=consumptionEquipment.get(ms.getMeasurer().getEquipment().getId()) + ms.getConsumption();
							consumptionEquipment.remove(ms.getMeasurer().getEquipment().getId());
							consumptionEquipment.put(ms.getMeasurer().getEquipment().getId(), consumption);
						}
						else{
							consumptionEquipment.put(ms.getMeasurer().getEquipment().getId(), (double) ms.getConsumption());
						}
				
				}
			}
		}
		Double consumptionTotal=(double) 0;
		Collection<Double> indirectConsumptions=consumptionEquipment.values();
		
		Iterator<Double> iterator = indirectConsumptions.iterator();
		 
       
        while (iterator.hasNext()) {
        	consumptionTotal=consumptionTotal+iterator.next();
       
        }
        
        Double consumptionByOrder=consumptionTotal/orders.size();
        for(Order o: orders){
        	ArrayList<OrderIndex> ordersIndexRange=(ArrayList<OrderIndex>) orderConsumptionRepository.getOrderByVariable(o.getId(),variableId);
        	
        	for(OrderIndex oi:ordersIndexRange){
        		oi.setIndirectConsumption((float) (consumptionByOrder/ordersIndexRange.size()));
        		oi.setIndirectIndex((oi.getDirectConsumption()+oi.getIndirectConsumption())
						/ o.getQuantity());
        		orderConsumptionRepository.save(oi);
        	}
        }
	}
}