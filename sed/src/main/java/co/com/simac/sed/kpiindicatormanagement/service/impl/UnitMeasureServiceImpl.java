package co.com.simac.sed.kpiindicatormanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.kpiindicatormanagement.dao.UnitMeasureRepository;
import co.com.simac.sed.kpiindicatormanagement.dao.VariableRepository;
import co.com.simac.sed.kpiindicatormanagement.dto.UnitMeasureDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.VariableDTO;
import co.com.simac.sed.kpiindicatormanagement.model.UnitMeasure;
import co.com.simac.sed.kpiindicatormanagement.service.UnitMeasureService;
import co.com.simac.sed.util.Utilities;

@Service
public class UnitMeasureServiceImpl implements UnitMeasureService {

	@Autowired
	private UnitMeasureRepository unitMeasureRepository;
	@Autowired
	private VariableRepository variableRepository;

	@Override
	public UnitMeasureDTO findById(Long id) {
		UnitMeasure unitMeasure = unitMeasureRepository.findOne(id);
		return unitMeasure.getDTO();
	}

	@Override
	public List<UnitMeasureDTO> findAll() {
		List<UnitMeasure> unitMeasures = unitMeasureRepository.findAll();
		return Utilities.toDTOList(unitMeasures, UnitMeasureDTO.class);
	}

	@Override
	public List<UnitMeasureDTO> findByVariableId(Long id) {
		VariableDTO variable = variableRepository.findOne(id).getDTO();
		if (variable == null) {
			return null;
		}
		List<UnitMeasureDTO> unitsVariable = variable.getUnitMeasuresDTO();
		return unitsVariable;
	}

}
