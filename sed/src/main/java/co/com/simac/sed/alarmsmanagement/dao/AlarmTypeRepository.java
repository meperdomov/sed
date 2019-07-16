package co.com.simac.sed.alarmsmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.simac.sed.alarmsmanagement.model.AlarmType;

/**
 * 
 * @author Maria E Perdomo SIMAC
 *
 */
@Repository
public interface AlarmTypeRepository extends JpaRepository<AlarmType, Long> {

}