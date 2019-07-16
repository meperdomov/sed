package co.com.simac.sed.kpiindicatormanagement.service;






import java.util.List;




import co.com.simac.sed.kpiindicatormanagement.dto.UnitMeasureDTO;





/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public interface UnitMeasureService  {

	
    public UnitMeasureDTO findById(Long id);
    public List<UnitMeasureDTO> findAll();
    public List<UnitMeasureDTO> findByVariableId(Long id);
    
}
