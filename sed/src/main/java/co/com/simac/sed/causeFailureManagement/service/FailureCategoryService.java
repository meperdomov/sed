package co.com.simac.sed.causeFailureManagement.service;

import java.util.List;

import co.com.simac.sed.causeFailureManagement.dto.FailureCategoryDTO;
import co.com.simac.sed.causeFailureManagement.model.FailureCategory;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface FailureCategoryService {

	public PageDTO<FailureCategoryDTO> getFailureCategoryPageDTO(int page,
			int size);

	public FailureCategoryDTO getFailureCategoryById(Long id);

	public FailureCategory save(FailureCategoryDTO indicador);
	
	public List<FailureCategoryDTO> findAll();

	public void delete(Long id);

}
