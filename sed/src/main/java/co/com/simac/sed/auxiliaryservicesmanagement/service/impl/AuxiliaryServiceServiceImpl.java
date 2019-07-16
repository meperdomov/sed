package co.com.simac.sed.auxiliaryservicesmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.auxiliaryservicesmanagement.dao.AuxiliaryServiceRepository;
import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceDTO;
import co.com.simac.sed.auxiliaryservicesmanagement.model.AuxiliaryService;
import co.com.simac.sed.auxiliaryservicesmanagement.service.AuxiliaryServiceService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class AuxiliaryServiceServiceImpl implements AuxiliaryServiceService {

	@Autowired
	private AuxiliaryServiceRepository auxiliaryServiceRepository;

	@Override
	public List<AuxiliaryServiceDTO> findAll() {
		ArrayList<AuxiliaryService> auxiliaryServices = (ArrayList<AuxiliaryService>) auxiliaryServiceRepository
				.findAll();
		return Utilities
				.toDTOList(auxiliaryServices, AuxiliaryServiceDTO.class);
	}

	@Override
	public AuxiliaryServiceDTO findById(Long id) {
		return auxiliaryServiceRepository.findOne(id).getDTO();
	}

	@Override
	public PageDTO<AuxiliaryServiceDTO> getAuxiliaryServicePageDTO(int page,
			int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<AuxiliaryService> auxiliaryServicePage = auxiliaryServiceRepository
				.findAll(pageable);
		return Utilities.toPageDTO(auxiliaryServicePage,
				AuxiliaryServiceDTO.class);
	}

	@Override
	public AuxiliaryServiceDTO getAuxiliarServiceById(Long id) {
		AuxiliaryService auxiliaryService = auxiliaryServiceRepository
				.findOne(id);
		return auxiliaryService.getDTO();
	}

	@Override
	public AuxiliaryService save(AuxiliaryServiceDTO auxiliaryServiceDTO) {
		AuxiliaryService auxiliaryService = new AuxiliaryService();
		Utilities.toEntity(auxiliaryService, auxiliaryServiceDTO);

		return auxiliaryServiceRepository.save(auxiliaryService);
	}

	@Override
	public void delete(Long id) {
		auxiliaryServiceRepository.delete(id);
	}

}
