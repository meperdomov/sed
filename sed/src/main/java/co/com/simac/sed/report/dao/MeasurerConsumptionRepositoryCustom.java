package co.com.simac.sed.report.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import co.com.simac.sed.util.PageDTO;

public interface MeasurerConsumptionRepositoryCustom {

	PageDTO<Object> getMeasurerConsumptionPage(Long idVariable,
			List<String> equipmentIds, Long idMeasurer, Long idUnitMeasure,
			Date initialDate, Date finalDate, Pageable pageable);

}
