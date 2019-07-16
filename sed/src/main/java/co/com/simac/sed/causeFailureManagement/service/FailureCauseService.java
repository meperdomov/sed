package co.com.simac.sed.causeFailureManagement.service;

import co.com.simac.sed.causeFailureManagement.dto.FailureCauseDTO;
import co.com.simac.sed.causeFailureManagement.model.FailureCause;
import co.com.simac.sed.util.PageDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
public interface FailureCauseService {

	public PageDTO<FailureCauseDTO> getFailureCausePageDTO(int page, int size);

	public FailureCauseDTO getFailureCauseById(Long id);

	public FailureCause save(FailureCauseDTO cause);

	public void delete(Long id);

}
