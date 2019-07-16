package co.com.simac.sed.graphics.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.graphics.service.TrendGraphKPIIndicatorService;
import co.com.simac.sed.kpiindicatormanagement.dao.UnitMeasureRepository;
import co.com.simac.sed.kpiindicatormanagement.model.UnitMeasure;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.dao.KPIHistorySpecifications;
import co.com.simac.sed.report.dto.KPIHistoryDTO;
import co.com.simac.sed.report.model.KPIHistory;
import co.com.simac.sed.util.Utilities;

@Service
public class TrendGraphKPIIndicatorServiceImpl implements TrendGraphKPIIndicatorService {

	@Autowired
	private KPIHistoryRepository kpiHistoryRepository;

	@Autowired
	private UnitMeasureRepository unitMeasuerRepository;

	@Override
	public KPIHistoryDTO getKPIHistoryById(Long id) {
		KPIHistory kpiHistory = kpiHistoryRepository.findOne(id);
		return kpiHistory.getDTO();
	}

	@Override
	public List<KPIHistoryDTO> getAllKPIHistoryByRange(Long idKpiIndicator, Long idVariable, Date initialDate,
			Date finalDate) {

		ArrayList<KPIHistory> KPIHistoryList = (ArrayList<KPIHistory>) kpiHistoryRepository
				.findAll(KPIHistorySpecifications.filter(idKpiIndicator, idVariable, initialDate, finalDate));
		return Utilities.toDTOList(KPIHistoryList, KPIHistoryDTO.class);
	}

	@Override
	public List<Object[]> getTrendGraph(Long idKpiIndicator, Long idVariable, Long idUnitMeasure, Date initialDate,
			Date finalDate) {
		List<Object[]> series = new ArrayList<>();
		UnitMeasure unitMeasure = unitMeasuerRepository.findOne(idUnitMeasure);
		ArrayList<KPIHistory> KPIHistoryList = (ArrayList<KPIHistory>) kpiHistoryRepository
				.findAll(KPIHistorySpecifications.filter(idKpiIndicator, idVariable, initialDate, finalDate));
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		for (KPIHistory kpiHistory : KPIHistoryList) {
			Object[] coordinate = new Object[2];
			String date = dateFormat.format(kpiHistory.getStartDate());
			coordinate[0] = date;
			coordinate[1] = kpiHistory.getAverageValue() * (unitMeasure != null ? unitMeasure.getConversionFactor() : 1);
			series.add(coordinate);
		}
		return series;
	}

}
