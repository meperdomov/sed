package co.com.simac.sed.kpiindicatormanagement.service;






import java.util.List;



import co.com.simac.sed.kpiindicatormanagement.dto.MaterialDTO;





/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */

public interface MaterialService  {

    public MaterialDTO findById(Long id);
    
    public List<MaterialDTO> filterByQuery(String name);
    
}
