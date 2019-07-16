package co.com.simac.sed.kpiindicatormanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.kpiindicatormanagement.dao.VariableRepository;
import co.com.simac.sed.kpiindicatormanagement.dto.VariableDTO;
import co.com.simac.sed.kpiindicatormanagement.model.Variable;
import co.com.simac.sed.kpiindicatormanagement.service.VariableService;
import co.com.simac.sed.util.Utilities;

@Service
public class VariableServiceImpl implements VariableService {

	@Autowired
	private VariableRepository variableRepository;
	
	@Override
	public List<VariableDTO> findAll() {
		ArrayList<Variable> variables = (ArrayList<Variable>) variableRepository.findAll();
		return Utilities.toDTOList(variables, VariableDTO.class);
	}

	@Override
	public VariableDTO findById(Long id) {
		return variableRepository.findOne(id).getDTO();
	}
}
