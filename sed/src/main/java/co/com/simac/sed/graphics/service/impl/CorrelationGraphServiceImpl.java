package co.com.simac.sed.graphics.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.graphics.dto.CorrelationGraphDTO;
import co.com.simac.sed.graphics.service.CorrelationGraphService;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.model.KPIHistory;

@Service
public class CorrelationGraphServiceImpl implements CorrelationGraphService {

	@Autowired
	private KPIHistoryRepository KPIHistoryRepository;

	@Override
	public CorrelationGraphDTO getCorrelationGraph(Long idKpiHistory) {

		CorrelationGraphDTO correlationGraphDTO = new CorrelationGraphDTO();
		KPIHistory kpiHistory = KPIHistoryRepository.findOne(idKpiHistory);
		if (kpiHistory != null) {
			ArrayList<KPIHistory> kpiHistories = new ArrayList<>();
			kpiHistories.add(kpiHistory);

			List<Object[]> series = new ArrayList<>();
			for (OrderIndex orderIndex : kpiHistory.getOrdersIndex()) {
				Object[] coordinate = new Object[2];
				coordinate[0] = orderIndex.getDirectConsumption();
				coordinate[1] = orderIndex.getQuantity();
				series.add(coordinate);
			}

			correlationGraphDTO.setId(idKpiHistory);
			correlationGraphDTO.setIntercept(kpiHistory.getIntercept());
			correlationGraphDTO.setSlope(kpiHistory.getSlope());
			correlationGraphDTO.setCorrelation(kpiHistory.getCorrelation());
			correlationGraphDTO.setSeries(series);
		}
		return correlationGraphDTO;
	}

}
