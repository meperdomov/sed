package co.com.simac.sed.kpiindicatormanagement.service;






import java.util.List;
import co.com.simac.sed.kpiindicatormanagement.dto.VariableDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public interface VariableService  {
	
    public VariableDTO findById(Long id);
	
    public List<VariableDTO> findAll();    
    
}
