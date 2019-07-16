package co.com.simac.sed.report.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.dao.OrderIndexRepository;
import co.com.simac.sed.cube.dto.OrderIndexDTO;
import co.com.simac.sed.cube.model.OrderIndex;
import co.com.simac.sed.kpiindicatormanagement.dao.UnitMeasureRepository;
import co.com.simac.sed.kpiindicatormanagement.model.UnitMeasure;
import co.com.simac.sed.report.dao.KPIHistoryRepository;
import co.com.simac.sed.report.dao.KPIHistorySpecifications;
import co.com.simac.sed.report.dto.KPIHistoryDTO;
import co.com.simac.sed.report.model.KPIHistory;
import co.com.simac.sed.report.service.KPIHistoryService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class KPIHistoryServiceImpl implements KPIHistoryService {

	@Autowired
	private KPIHistoryRepository kpiHistoryRepository;

	@Autowired
	private OrderIndexRepository orderIndexRepository;

	@Autowired
	private UnitMeasureRepository unitMeasuerRepository;

	@Override
	public KPIHistoryDTO findById(Long id) {
		KPIHistory kpiHistory = kpiHistoryRepository.findOne(id);
		return kpiHistory.getDTO();
	}

	@Override
	public PageDTO<KPIHistoryDTO> getKPIHistoryPageDTO(int page, int size, Long idKpiIndicator, Long idUnitMeasure,
			Date initialDate, Date finalDate) {
		Pageable pageable = new PageRequest(page - 1, size);

		ArrayList<KPIHistory> KPIHistoryList = (ArrayList<KPIHistory>) kpiHistoryRepository
				.findAll(KPIHistorySpecifications.filter(idKpiIndicator, new Long(-1), initialDate, finalDate));

		if (idUnitMeasure != -1) {
			UnitMeasure unitMeasure = unitMeasuerRepository.findOne(idUnitMeasure);
			for (KPIHistory kpiHistory : KPIHistoryList) {
				kpiHistory.setAverageValue(kpiHistory.getAverageValue() * unitMeasure.getConversionFactor());
			}
		}

		Page<KPIHistory> KPIHistoryPage = new PageImpl<KPIHistory>(KPIHistoryList, pageable, KPIHistoryList.size());
		return Utilities.toPageDTO(KPIHistoryPage, KPIHistoryDTO.class);
	}

	@Override
	public PageDTO<KPIHistoryDTO> getRecentsKPIHistoryPageDTO(int page, int size, Date initialDate, Date finalDate) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<KPIHistory> kpiHistoryPage = kpiHistoryRepository.findRecentsKpiHistories(initialDate, finalDate,
				pageable);
		return Utilities.toPageDTO(kpiHistoryPage, KPIHistoryDTO.class);
	}

	@Override
	public PageDTO<OrderIndexDTO> getOrdersIndexByKPIHistoryPageDTO(int page, int size, long idKpiHistory) {
		Pageable pageable = new PageRequest(page - 1, size);
		ArrayList<KPIHistory> kpiHistories = new ArrayList<>();
		KPIHistory kpiHistory = new KPIHistory();
		kpiHistory.setId(idKpiHistory);
		kpiHistories.add(kpiHistory);
		
		Page<OrderIndex> orderIndexPage = orderIndexRepository.findOrdersIndexByKpiHistory(kpiHistories, pageable);
		return Utilities.toPageDTO(orderIndexPage, OrderIndexDTO.class);

	}

}
