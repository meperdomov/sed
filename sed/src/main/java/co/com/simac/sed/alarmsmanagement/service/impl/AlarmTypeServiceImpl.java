package co.com.simac.sed.alarmsmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.alarmsmanagement.dao.AlarmTypeRepository;
import co.com.simac.sed.alarmsmanagement.dto.AlarmTypeDTO;
import co.com.simac.sed.alarmsmanagement.model.AlarmType;
import co.com.simac.sed.alarmsmanagement.service.AlarmTypeService;
import co.com.simac.sed.util.Utilities;

@Service
public class AlarmTypeServiceImpl implements AlarmTypeService {

	@Autowired
	private AlarmTypeRepository alarmTypeRepository;

	@Override
	public AlarmTypeDTO findById(Long id) {
		AlarmType alarmType = alarmTypeRepository.findOne(id);
		return alarmType.getDTO();
	}

	@Override
	public List<AlarmTypeDTO> findAll() {
		ArrayList<AlarmType> alarmsType = (ArrayList<AlarmType>) alarmTypeRepository
				.findAll();
		return Utilities.toDTOList(alarmsType, AlarmTypeDTO.class);
	}

}