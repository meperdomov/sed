package co.com.simac.sed.causeFailureManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.causeFailureManagement.dao.FailureCauseRepository;
import co.com.simac.sed.causeFailureManagement.dto.FailureCauseDTO;
import co.com.simac.sed.causeFailureManagement.model.FailureCause;
import co.com.simac.sed.causeFailureManagement.service.FailureCauseService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class FailureCauseServiceImpl implements FailureCauseService {

	@Autowired
	private FailureCauseRepository failureCauseRepository;

	@Override
	public PageDTO<FailureCauseDTO> getFailureCausePageDTO(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<FailureCause> causeFailurePage = failureCauseRepository
				.findAll(pageable);
		return Utilities.toPageDTO(causeFailurePage, FailureCauseDTO.class);
	}

	@Override
	public FailureCauseDTO getFailureCauseById(Long id) {
		FailureCause causeFailure = failureCauseRepository.findOne(id);
		return causeFailure.getDTO();
	}

	@Override
	public FailureCause save(FailureCauseDTO failureCauseDTO) {
		FailureCause failureCause = new FailureCause();
		Utilities.toEntity(failureCause, failureCauseDTO);

		return failureCauseRepository.save(failureCause);
	}

	@Override
	public void delete(Long id) {
		failureCauseRepository.delete(id);
	}

}
