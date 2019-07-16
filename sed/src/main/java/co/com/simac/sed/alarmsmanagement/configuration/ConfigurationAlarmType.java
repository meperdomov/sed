package co.com.simac.sed.alarmsmanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import co.com.simac.sed.alarmsmanagement.dao.AlarmTypeRepository;
import co.com.simac.sed.alarmsmanagement.model.AlarmType;

/**
 * 
 * @author Juan Camilo
 *
 */
@Component
public class ConfigurationAlarmType implements ApplicationRunner {

	@Autowired
	private AlarmTypeRepository alarmTypeRepository;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {

		/* Se crean tipos de alarmas por defecto */

		AlarmType alarmType1 = new AlarmType();
		alarmType1.setId(new Long(1));
		alarmType1.setName("Límites de control");

		alarmTypeRepository.save(alarmType1);

		AlarmType alarmType2 = new AlarmType();
		alarmType2.setId(new Long(2));
		alarmType2.setName("Correlación");

		alarmTypeRepository.save(alarmType2);

		AlarmType alarmType3 = new AlarmType();
		alarmType3.setId(new Long(3));
		alarmType3.setName("Variación del indicador");
		
		alarmTypeRepository.save(alarmType3);
	}
}
