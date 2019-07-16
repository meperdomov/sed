package co.com.simac.sed.report.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.model.Equipment;
import co.com.simac.sed.kpiindicatormanagement.dao.EquipmentRepository;
import co.com.simac.sed.report.dao.MeasurerConsumptionRepositoryCustom;
import co.com.simac.sed.report.service.MeasurerConsumptionService;
import co.com.simac.sed.util.PageDTO;

@Service
public class MeasurerConsumptionServiceImpl implements MeasurerConsumptionService {

	@Autowired
	private MeasurerConsumptionRepositoryCustom measurerConsumptionRepositoryCustom;

	@Autowired
	EquipmentRepository equipmentRepository;

	@Override
	public PageDTO<Object> getMeasurerConsumptionPage(int page, int size, long idVariable, long idEquipment,
			long idMeasurer, long idUnitMeasure, Date initialDate, Date finalDate) {
		Pageable pageable = new PageRequest(page - 1, size);

		Equipment equipment = equipmentRepository.findOne(idEquipment);
		List<String> equipmentIds = new ArrayList<>();
		getEquipmentIds(equipment, equipmentIds);
		PageDTO<Object> measurerConsumptionPage = measurerConsumptionRepositoryCustom.getMeasurerConsumptionPage(
				idVariable, equipmentIds, idMeasurer, idUnitMeasure, initialDate, finalDate, pageable);
		return measurerConsumptionPage;
	}

	private void getEquipmentIds(Equipment parent, List<String> equipmentIds) {
		if (parent != null) {
			List<Equipment> equipments = parent.getChildrens();
			for (Equipment equipment : equipments) {
				equipmentIds.add(String.valueOf(equipment.getId()));
				getEquipmentIds(equipment, equipmentIds);
			}
		}
	}
}
