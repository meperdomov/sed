package co.com.simac.sed.cube.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.dao.MeasurerRepository;
import co.com.simac.sed.cube.dto.MeasurerDTO;
import co.com.simac.sed.cube.model.Measurer;
import co.com.simac.sed.cube.service.MeasurerService;
import co.com.simac.sed.util.Utilities;

@Service
public class MeasurerServiceImpl implements MeasurerService {

	@Autowired
	private MeasurerRepository measurerRepository;

	@Override
	public List<MeasurerDTO> findByEquipmentId(Long id) {
		ArrayList<Measurer> measurers = (ArrayList<Measurer>) measurerRepository
				.findMeasurerByEquipment(id);

		return Utilities.toDTOList(measurers, MeasurerDTO.class);
	}
}
