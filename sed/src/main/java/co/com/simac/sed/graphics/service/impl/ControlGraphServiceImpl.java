package co.com.simac.sed.graphics.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.graphics.dto.ControlGraphDTO;
import co.com.simac.sed.graphics.service.ControlGraphService;
import co.com.simac.sed.kpiindicatormanagement.dao.UnitMeasureRepository;
import co.com.simac.sed.kpiindicatormanagement.model.UnitMeasure;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.dao.KPIHistorySpecifications;
import co.com.simac.sed.report.model.KPIHistory;

@Service
public class ControlGraphServiceImpl implements ControlGraphService {

	@Autowired
	private KPIHistoryRepository kpiHistoryRepository;

	@Autowired
	private UnitMeasureRepository unitMeasuerRepository;

	@Override
	public ControlGraphDTO getControlGraph(Long idKpiIndicator,
			Long idVariable, Long idUnitMeasure, Date initialDate,
			Date finalDate) {
		List<Object[]> series = new ArrayList<>();
		UnitMeasure unitMeasure = unitMeasuerRepository.findOne(idUnitMeasure);
		ArrayList<KPIHistory> KPIHistoryList = (ArrayList<KPIHistory>) kpiHistoryRepository
				.findAll(KPIHistorySpecifications.filter(idKpiIndicator,
						idVariable, initialDate, finalDate));
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Float lowerLimit = (float) 0;
		Float upperLimit = (float) 0;
		for (KPIHistory kpiHistory : KPIHistoryList) {
			Object[] coordinate = new Object[2];
			String date = dateFormat.format(kpiHistory.getStartDate());
			coordinate[0] = date;
			coordinate[1] = kpiHistory.getAverageValue()
					* (unitMeasure != null ? unitMeasure.getConversionFactor()
							: 1);
			series.add(coordinate);
//			if(kpiHistory.getKpiindicator().getIsFactor()!=null && kpiHistory.getKpiindicator().getIsFactor() ==true){
//				lowerLimit = (float) (kpiHistory.getKpiindicator().getLowerControlLimit()*kpiHistory.getDeviation());
//				upperLimit = (float) (kpiHistory.getKpiindicator().getUpperControlLimit()*kpiHistory.getDeviation());
//			}
//			else{
//				lowerLimit = kpiHistory.getKpiindicator().getLowerControlLimit();
//				upperLimit = kpiHistory.getKpiindicator().getUpperControlLimit();
//			}
		}
		ControlGraphDTO controlGraphDTO = new ControlGraphDTO();
		controlGraphDTO.setSeries(series);
		controlGraphDTO.setLowerLimit(lowerLimit.doubleValue());
		controlGraphDTO.setUpperLimit(upperLimit.doubleValue());
		controlGraphDTO.setId(idKpiIndicator);
		return controlGraphDTO;
	}

}
