package co.com.simac.sed.graphics.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.graphics.dto.ConsumptionProductionGraphDTO;
import co.com.simac.sed.graphics.service.ConsumptionProductionGraphService;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.model.KPIHistory;

@Service
public class ConsumptionProductionGraphServiceImpl implements ConsumptionProductionGraphService {

	@Autowired
	private KPIHistoryRepository kpiHistoryRepository;

	@Override
	public ConsumptionProductionGraphDTO getConsumptionProductionGraph(Long idKpiHistory) {
		ConsumptionProductionGraphDTO consumptionProductionGraphDTO=new ConsumptionProductionGraphDTO();
		List<Object[]> serieConsumption = new ArrayList<>();
		List<Object[]> serieProduction = new ArrayList<>();
		KPIHistory kpiHistory = kpiHistoryRepository.findOne(idKpiHistory);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if (kpiHistory != null) {
			ArrayList<KPIHistory> kpiHistories = new ArrayList<>();
			kpiHistories.add(kpiHistory);
			for (OrderIndex orderIndex : kpiHistory.getOrdersIndex()) {
				Object[] coordinateConsumption = new Object[2];
				Object[] coordinateProduction = new Object[2];
				String date = dateFormat.format(orderIndex.getOrder().getStartDate());
				coordinateConsumption[0] = date;
				coordinateConsumption[1] = orderIndex.getDirectConsumption();
				serieConsumption.add(coordinateConsumption);
				coordinateProduction[0] = date;
				coordinateProduction[1]=orderIndex.getQuantity();
				serieProduction.add(coordinateProduction);
			}
			consumptionProductionGraphDTO.setSerieConsumption(serieConsumption);
			consumptionProductionGraphDTO.setSerieProduction(serieProduction);
		}
		return consumptionProductionGraphDTO;
	}

}
