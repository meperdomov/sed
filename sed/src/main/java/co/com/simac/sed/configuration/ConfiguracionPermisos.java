package co.com.simac.sed.configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import co.com.simac.sed.security.dao.RoleRepository;
import co.com.simac.sed.security.dao.UserRepository;
import co.com.simac.sed.security.model.Permission;
import co.com.simac.sed.security.model.Role;
import co.com.simac.sed.security.model.User;

/**
 * 
 * @author Juan Camilo
 *
 */
@Component
public class ConfiguracionPermisos implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/* Se crea el rol administrador */
		Role role = new Role();
		role.setId(1l);
		role.setName("Administrador");
		role.setPermissions(createPermissions());
		roleRepository.save(role);

		/* Se crea el usuario por defecto del sistema */
		User user = new User();
		user.setId(1l);
		user.setName("Administrador");
		user.setEmail("info@simac.com.co");
		user.setUserName("admin");
		user.setPassword("123");
		user.setRole(role);
		userRepository.save(user);
	}

	private List<Permission> createPermissions() {
		LinkedHashMap<String, String> permissions = new LinkedHashMap<>();

		/**
		 * Permisos para configuracion de indicadores KPI
		 */
		permissions.put("ROLE_KPIINDICATOR_LIST", "Listar");
		permissions.put("ROLE_KPIINDICATOR_GET", "Detalle");
		permissions.put("ROLE_KPIINDICATOR_SAVE", "Crear/editar");
		permissions.put("ROLE_KPIINDICATOR_DELETE", "Eliminar");

		/**
		 * Permisos para alarmas por indicador
		 */
		permissions.put("ROLE_KPIINDICATOR_ALARM_LIST", "Listar");
		permissions.put("ROLE_KPIINDICATOR_ALARM_GET", "Detalle");
		permissions.put("ROLE_KPIINDICATOR_ALARM_SAVE", "Crear/editar");
		permissions.put("ROLE_KPIINDICATOR_ALARM_DELETE", "Eliminar");

		/**
		 * Permisos para configuracion de servicios auxiliares
		 */
		permissions.put("ROLE_AUXSERVICE_LIST", "Listar");
		permissions.put("ROLE_AUXSERVICE_GET", "Detalle");
		permissions.put("ROLE_AUXSERVICE_SAVE", "Crear/editar");
		permissions.put("ROLE_AUXSERVICE_DELETE", "Eliminar");

		permissions.put("ROLE_AUXSERVICECONFIG_LIST", "Listar");
		permissions.put("ROLE_AUXSERVICECONFIG_GET", "Detalle");
		permissions.put("ROLE_AUXSERVICECONFIG_SAVE", "Crear/editar");
		permissions.put("ROLE_AUXSERVICECONFIG_DELETE", "Eliminar");

		/**
		 * Permisos para configuracion de usuarios y roles
		 */
		permissions.put("ROLE_ROLE_LIST", "Listar");
		permissions.put("ROLE_ROLE_GET", "Detalle");
		permissions.put("ROLE_ROLE_SAVE", "Crear/editar");
		permissions.put("ROLE_ROLE_DELETE", "Eliminar");

		permissions.put("ROLE_USER_LIST", "Listar");
		permissions.put("ROLE_USER_GET", "Detalle");
		permissions.put("ROLE_USER_SAVE", "Crear/editar");
		permissions.put("ROLE_USER_DELETE", "Eliminar");

		/**
		 * Permisos para el cubo de datos
		 */
		permissions.put("ROLE_CUBE", "Cubo de datos");

		/**
		 * Graficos
		 */
		permissions.put("ROLE_TRENDKPI_GRAPH", "Grafico de tendencias KPI");
		permissions.put("ROLE_CORRELATION_GRAPH", "Grafico de Correlacion");
		permissions.put("ROLE_CONSUMPTIONPRODUCTION_GRAPH", "Grafico de Consumo vs Produccion");

		/**
		 * Reportes
		 */
		permissions.put("ROLE_KPIHISTORY_LIST", "Listar");
		permissions.put("ROLE_KPIHISTORY_GET", "Detalle");

		permissions.put("ROLE_ALARMHISTORY_LIST", "Listar");

		permissions.put("ROLE_MEASURERCONSUMPTION_LIST", "Listar");
		permissions.put("ROLE_SEMAPHOREBOARD", "Tablero de semaforos");

		/**
		 * Permisos para la configuracion de categorias y causas de falla
		 */
		permissions.put("ROLE_FAILURE_CATEGORY_LIST", "Listar");
		permissions.put("ROLE_FAILURE_CATEGORY_GET", "Detalle");
		permissions.put("ROLE_FAILURE_CATEGORY_SAVE", "Crear/editar");
		permissions.put("ROLE_FAILURE_CATEGORY_DELETE", "Eliminar");

		permissions.put("ROLE_FAILURE_CAUSE_LIST", "Listar");
		permissions.put("ROLE_FAILURE_CAUSE_GET", "Detalle");
		permissions.put("ROLE_FAILURE_CAUSE_SAVE", "Crear/editar");
		permissions.put("ROLE_FAILURE_CAUSE_DELETE", "Eliminar");

		/**
		 * Permisos para los tableros
		 */
		permissions.put("ROLE_BOARD_LIST", "Listar");
		permissions.put("ROLE_BOARD_GET", "Detalle");
		permissions.put("ROLE_BOARD_SAVE", "Crear/editar");
		permissions.put("ROLE_BOARD_DELETE", "Eliminar");

		/**
		 * Permisos para configuracion de parametros globales
		 */
		permissions.put("ROLE_PARAMETER_CONF_LIST", "Listar");
		permissions.put("ROLE_PARAMETER_CONF_GET", "Detalle");
		permissions.put("ROLE_PARAMETER_CONF_SAVE", "Crear/editar");
		permissions.put("ROLE_PARAMETER_CONF_DELETE", "Eliminar");
		
		/**
		 * Permisos para atender alarmas presentes
		 */
		permissions.put("ROLE_ALARMPRESENT_LIST", "Listar");
		permissions.put("ROLE_ALARMPRESENT_SAVE", "Atender alarma");
		

		List<Permission> permissionsList = new ArrayList<>();

		int i = 1;
		for (String code : permissions.keySet()) {
			String name = permissions.get(code);
			Permission permission = new Permission();
			permission.setId(new Long(i));
			permission.setName(name);
			permission.setCode(code);
			permissionsList.add(permission);
			i++;
		}
		return permissionsList;
	}
}
