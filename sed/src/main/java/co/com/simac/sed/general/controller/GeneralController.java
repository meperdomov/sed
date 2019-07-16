package co.com.simac.sed.general.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.simac.sed.alarmsmanagement.dto.AlarmTypeDTO;
import co.com.simac.sed.alarmsmanagement.dto.KPIIndicatorAlarmDTO;
import co.com.simac.sed.alarmsmanagement.service.AlarmTypeService;
import co.com.simac.sed.alarmsmanagement.service.KPIIndicatorAlarmService;
import co.com.simac.sed.auxiliaryservicesmanagement.dto.AuxiliaryServiceDTO;
import co.com.simac.sed.auxiliaryservicesmanagement.service.AuxiliaryServiceService;
import co.com.simac.sed.causeFailureManagement.dto.FailureCategoryDTO;
import co.com.simac.sed.causeFailureManagement.service.FailureCategoryService;
import co.com.simac.sed.cube.dto.MeasurerDTO;
import co.com.simac.sed.cube.model.Equipment;
import co.com.simac.sed.cube.model.Material;
import co.com.simac.sed.cube.service.MeasurerService;
import co.com.simac.sed.cube.service.QuantityOrdersService;
import co.com.simac.sed.enums.Parameter;
import co.com.simac.sed.enums.Priority;
import co.com.simac.sed.kpiindicatormanagement.dto.EquipmentDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.KPIIndicatorDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.MaterialDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.RouteDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.UnitMeasureDTO;
import co.com.simac.sed.kpiindicatormanagement.dto.VariableDTO;
import co.com.simac.sed.kpiindicatormanagement.service.EquipmentService;
import co.com.simac.sed.kpiindicatormanagement.service.KPIIndicatorService;
import co.com.simac.sed.kpiindicatormanagement.service.MaterialService;
import co.com.simac.sed.kpiindicatormanagement.service.RouteService;
import co.com.simac.sed.kpiindicatormanagement.service.VariableService;
import co.com.simac.sed.kpiindicatormanagement.service.impl.UnitMeasureServiceImpl;
import co.com.simac.sed.security.dto.PermissionDTO;
import co.com.simac.sed.security.dto.RoleDTO;
import co.com.simac.sed.security.service.RoleService;

@RestController
@RequestMapping("/general")
public class GeneralController {

	@Autowired
	private VariableService variableService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private UnitMeasureServiceImpl unitService;

	@Autowired
	private AuxiliaryServiceService auxiliaryServiceService;

	@Autowired
	private KPIIndicatorService kpiIndicatorService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MeasurerService measurerService;

	@Autowired
	private QuantityOrdersService quantityService;

	@Autowired
	private AlarmTypeService alarmTypeService;

	@Autowired
	private FailureCategoryService failureCategoryService;

	@Autowired
	private KPIIndicatorAlarmService kpiIndicatorAlarmService;

	@RequestMapping(value = "/variables", method = RequestMethod.GET)
	public ResponseEntity<List<VariableDTO>> listAllVariables() {
		List<VariableDTO> variables = variableService.findAll();
		if (variables.isEmpty()) {
			return new ResponseEntity<List<VariableDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<VariableDTO>>(variables, HttpStatus.OK);
	}

	@RequestMapping(value = "/equipments", method = RequestMethod.GET)
	public ResponseEntity<List<EquipmentDTO>> listParentEquipments() {
		List<EquipmentDTO> equipments = equipmentService.findAllParents();
		if (equipments.isEmpty()) {
			return new ResponseEntity<List<EquipmentDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EquipmentDTO>>(equipments, HttpStatus.OK);
	}

	@RequestMapping(value = "/equipments/{id}/childrens", method = RequestMethod.GET)
	public ResponseEntity<List<EquipmentDTO>> listChildrens(@PathVariable("id") Long id) {
		Equipment parent = new Equipment();
		parent.setId(id);
		List<EquipmentDTO> childrens = equipmentService.findChildrens(parent);
		if (childrens.isEmpty()) {
			return new ResponseEntity<List<EquipmentDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EquipmentDTO>>(childrens, HttpStatus.OK);
	}

	@RequestMapping(value = "/materials", method = RequestMethod.GET)
	public ResponseEntity<List<MaterialDTO>> filterMaterialsByQuery(@RequestParam(value = "query") String query) {
		List<MaterialDTO> materials = materialService.filterByQuery(query);
		if (materials.isEmpty()) {
			return new ResponseEntity<List<MaterialDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MaterialDTO>>(materials, HttpStatus.OK);
	}

	@RequestMapping(value = "/materials/{id}/routes", method = RequestMethod.GET)
	public ResponseEntity<List<RouteDTO>> filterRoutesByMaterial(@PathVariable("id") Long id) {
		Material material = new Material();
		material.setId(id);
		List<RouteDTO> routes = routeService.findByMaterial(material);
		if (routes.isEmpty()) {
			return new ResponseEntity<List<RouteDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RouteDTO>>(routes, HttpStatus.OK);
	}

	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public ResponseEntity<List<RouteDTO>> filterRoutesByQuery(@RequestParam(value = "query") String query) {
		List<RouteDTO> routes = routeService.filterByQuery(query);
		if (routes.isEmpty()) {
			return new ResponseEntity<List<RouteDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RouteDTO>>(routes, HttpStatus.OK);
	}

	@RequestMapping(value = "/units", method = RequestMethod.GET)
	public ResponseEntity<List<UnitMeasureDTO>> listAllUnits() {
		List<UnitMeasureDTO> units = unitService.findAll();
		if (units.isEmpty()) {
			return new ResponseEntity<List<UnitMeasureDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UnitMeasureDTO>>(units, HttpStatus.OK);
	}

	@RequestMapping(value = "/variables/{id}/units", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UnitMeasureDTO>> getUnitMeasureByVariable(@PathVariable("id") long id) {
		List<UnitMeasureDTO> units = unitService.findByVariableId(id);
		if (units == null) {
			return new ResponseEntity<List<UnitMeasureDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UnitMeasureDTO>>(units, HttpStatus.OK);
	}

	@RequestMapping(value = "/auxiliaryservices", method = RequestMethod.GET)
	public ResponseEntity<List<AuxiliaryServiceDTO>> listAllAuxiliaryServices() {
		List<AuxiliaryServiceDTO> auxiliaryServices = auxiliaryServiceService.findAll();
		if (auxiliaryServices.isEmpty()) {
			return new ResponseEntity<List<AuxiliaryServiceDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AuxiliaryServiceDTO>>(auxiliaryServices, HttpStatus.OK);
	}

	@RequestMapping(value = "/indicators", method = RequestMethod.GET)
	public ResponseEntity<List<KPIIndicatorDTO>> listActiveIndicators() {
		List<KPIIndicatorDTO> indicators = kpiIndicatorService.findByActiveTrue();
		if (indicators.isEmpty()) {
			return new ResponseEntity<List<KPIIndicatorDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KPIIndicatorDTO>>(indicators, HttpStatus.OK);
	}

	@RequestMapping(value = "/priorities", method = RequestMethod.GET)
	public ResponseEntity<Priority[]> listAllPrioritys() {
		Priority[] prioritys = Priority.values();

		return new ResponseEntity<Priority[]>(prioritys, HttpStatus.OK);
	}

	@RequestMapping(value = "/parameters", method = RequestMethod.GET)
	public ResponseEntity<Parameter[]> listAllParameters() {
		Parameter[] parameters = Parameter.values();
		return new ResponseEntity<Parameter[]>(parameters, HttpStatus.OK);
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<List<RoleDTO>> listAllRoles() {
		List<RoleDTO> roles = roleService.findAll();
		return new ResponseEntity<List<RoleDTO>>(roles, HttpStatus.OK);
	}

	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public ResponseEntity<List<PermissionDTO>> listAllPermissions() {
		List<PermissionDTO> permissions = roleService.findAllPermissions();
		if (permissions.isEmpty()) {
			return new ResponseEntity<List<PermissionDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PermissionDTO>>(permissions, HttpStatus.OK);
	}

	@RequestMapping(value = "/variables/{id}/indicators", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KPIIndicatorDTO>> getIndicatorsByVariableId(@PathVariable("id") long id) {
		List<KPIIndicatorDTO> kpiIndicators = kpiIndicatorService.findByVariableId(id);
		if (kpiIndicators == null) {
			return new ResponseEntity<List<KPIIndicatorDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KPIIndicatorDTO>>(kpiIndicators, HttpStatus.OK);
	}

	@RequestMapping(value = "/stages", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listAllStages() {
		List<String> stages = kpiIndicatorService.findStageAll();
		if (stages.isEmpty()) {
			return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<String>>(stages, HttpStatus.OK);
	}

	@RequestMapping(value = "/equipments/{id}/measurers", method = RequestMethod.GET)
	public ResponseEntity<List<MeasurerDTO>> filterMeasurersByEquipment(@PathVariable("id") Long id) {

		List<MeasurerDTO> measurers = measurerService.findByEquipmentId(id);
		if (measurers.isEmpty()) {
			return new ResponseEntity<List<MeasurerDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MeasurerDTO>>(measurers, HttpStatus.OK);
	}

	@RequestMapping(value = "/quantity/successfulorders", method = RequestMethod.GET)
	public ResponseEntity<Integer> getQuantityOrdersSuccesful(
			@RequestParam(value = "initialDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date initialDate,
			@RequestParam(value = "finalDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date finalDate) {

		Integer quantity = quantityService.getCountOrdersSuccessful();
		if (quantity == 0) {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(quantity, HttpStatus.OK);
	}

	@RequestMapping(value = "/quantity/badorders", method = RequestMethod.GET)
	public ResponseEntity<Integer> getQuantityOrdersError() {

		Integer quantity = quantityService.getCountOrdersError();
		if (quantity == 0) {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(quantity, HttpStatus.OK);
	}

	@RequestMapping(value = "/quantity/activekpi", method = RequestMethod.GET)
	public ResponseEntity<Integer> getTotalActivesKPI() {

		Integer quantity = quantityService.getTotalActiveKPI();
		if (quantity == 0) {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(quantity, HttpStatus.OK);
	}

	@RequestMapping(value = "/alarmtypes", method = RequestMethod.GET)
	public ResponseEntity<List<AlarmTypeDTO>> listAllAlarmsType() {
		List<AlarmTypeDTO> alarmsType = alarmTypeService.findAll();
		if (alarmsType.isEmpty()) {
			return new ResponseEntity<List<AlarmTypeDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AlarmTypeDTO>>(alarmsType, HttpStatus.OK);
	}

	@RequestMapping(value = "/failurecategories", method = RequestMethod.GET)
	public ResponseEntity<List<FailureCategoryDTO>> listAllFailureCategories() {
		List<FailureCategoryDTO> failureCategories = failureCategoryService.findAll();
		if (failureCategories.isEmpty()) {
			return new ResponseEntity<List<FailureCategoryDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FailureCategoryDTO>>(failureCategories, HttpStatus.OK);
	}

	@RequestMapping(value = "/indicators/{id}/kpiindicatoralarms", method = RequestMethod.GET)
	public ResponseEntity<List<KPIIndicatorAlarmDTO>> filterKpiAlarmsByIndicator(@PathVariable("id") Long id) {
		List<KPIIndicatorAlarmDTO> kpIndicatorAlarmsDTO = kpiIndicatorAlarmService
				.getKPIIndicatorAlarmByIndicatorId(id);
		if (kpIndicatorAlarmsDTO.isEmpty()) {
			return new ResponseEntity<List<KPIIndicatorAlarmDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KPIIndicatorAlarmDTO>>(kpIndicatorAlarmsDTO, HttpStatus.OK);
	}
}