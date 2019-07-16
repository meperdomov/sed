package co.com.simac.sed.alarmsmanagement.service;

import java.util.List;
import co.com.simac.sed.alarmsmanagement.dto.AlarmTypeDTO;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public interface AlarmTypeService  {

    public AlarmTypeDTO findById(Long id);
    
    public List<AlarmTypeDTO> findAll();
    
}
