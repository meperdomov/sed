package co.com.simac.sed.causeFailureManagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.simac.sed.causeFailureManagement.dao.FailureCategoryRepository;
import co.com.simac.sed.causeFailureManagement.dto.FailureCategoryDTO;
import co.com.simac.sed.causeFailureManagement.dto.FailureCauseDTO;
import co.com.simac.sed.causeFailureManagement.model.FailureCategory;
import co.com.simac.sed.causeFailureManagement.model.FailureCause;
import co.com.simac.sed.causeFailureManagement.service.FailureCategoryService;
import co.com.simac.sed.util.PageDTO;
import co.com.simac.sed.util.Utilities;

@Service
public class FailureCategoryServiceImpl implements FailureCategoryService {

	@Autowired
	private FailureCategoryRepository failureCategoryRepository;

	@Override
	public PageDTO<FailureCategoryDTO> getFailureCategoryPageDTO(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		Page<FailureCategory> failureCategoryPage = failureCategoryRepository.findAll(pageable);
		return Utilities.toPageDTO(failureCategoryPage, FailureCategoryDTO.class);
	}

	@Override
	public FailureCategoryDTO getFailureCategoryById(Long id) {
		FailureCategory failureCategory = failureCategoryRepository.findOne(id);
		return failureCategory.getDTO();
	}

	@Override
	public FailureCategory save(FailureCategoryDTO failureCategoryDTO) {
		FailureCategory failureCategory = new FailureCategory();
		Utilities.toEntity(failureCategory, failureCategoryDTO);
		failureCategory.setFailureCauses(populateCauses(failureCategoryDTO));
		return failureCategoryRepository.save(failureCategory);
	}

	private List<FailureCause> populateCauses(FailureCategoryDTO failureCategoryDTO) {
		List<FailureCause> causes = new ArrayList<>();
		for (FailureCauseDTO failureCauseDTO : failureCategoryDTO.getFailureCausesDTO()) {
			FailureCause failureCause = new FailureCause();
			Utilities.toEntity(failureCause, failureCauseDTO);
			causes.add(failureCause);
		}
		return causes;
	}

	@Override
	public void delete(Long id) {
		failureCategoryRepository.delete(id);
	}

	@Override
	public List<FailureCategoryDTO> findAll() {
		ArrayList<FailureCategory> failureCategories = (ArrayList<FailureCategory>) failureCategoryRepository.findAll();
		return Utilities.toDTOList(failureCategories, FailureCategoryDTO.class);
	}

}
