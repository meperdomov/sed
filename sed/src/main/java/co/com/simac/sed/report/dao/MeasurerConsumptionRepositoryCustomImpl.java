package co.com.simac.sed.report.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import co.com.simac.sed.util.PageDTO;

@Repository
public class MeasurerConsumptionRepositoryCustomImpl implements MeasurerConsumptionRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public PageDTO<Object> getMeasurerConsumptionPage(final Long idVariable, List<String> equipmentIds, Long idMeasurer,
			Long idUnitMeasure, final Date initialDate, final Date finalDate, Pageable pageable) {

		List<String> columns = new ArrayList<>();
		List<String> selectParams = new ArrayList<>();
		List<String> whereParams = new ArrayList<>();
		List<String> groupParams = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String initialDateString = sdf.format(initialDate);
		String finalDateString = sdf.format(finalDate);

		if (idVariable != -1) {
			if (idUnitMeasure != -1) {
				selectParams.add(" um.nombre as variable");
				groupParams.add(" um.nombre ");
			} else {
				selectParams.add(" v.nombre as variable");
				groupParams.add(" v.nombre ");
				whereParams.add(" v.id = " + idVariable);
			}
			columns.add("Variable");
		}

		if (!equipmentIds.isEmpty()) {
			selectParams.add(" e.nombre as equipamiento");
			groupParams.add(" e.nombre ");
			whereParams.add(" e.id IN (" + String.join(" , ", equipmentIds) + ")");
			columns.add("Equipamiento");
		}

		if (idMeasurer != -1) {
			selectParams.add(" me.nombre as medidor ");
			groupParams.add(" me.nombre ");
			whereParams.add(" me.id = " + idMeasurer);
			columns.add("Medidor");
		}

		columns.add("Consumo");

		if (initialDate != null && !StringUtils.isEmpty(initialDate)) {
			whereParams.add(" c.fecha_inicio >= '" + initialDateString + "'");
		}

		if (finalDate != null && !StringUtils.isEmpty(finalDate)) {
			whereParams.add(" c.fecha_inicio <= '" + finalDateString + "'");
		}

		String sql1 = "select " + ((!selectParams.isEmpty()) ? String.join(",", selectParams) + "," : "")
				+ " sum(consumo) "
				+ ((idUnitMeasure != -1)
						? " * (select factor_conversion from unidad_medida where id = " + idUnitMeasure
								+ ") as consumo "
						: " as consumo ")
				+ " from consumo_medidor as c " 
				+ " inner join variable as v on v.id=c.variable_id "
				+ ((idUnitMeasure != -1) ? " left join unidad_medida as um on um.id = "+idUnitMeasure+" " : "") 
				+ " inner join medidor as me on me.id=c.medidor_id "
				+ " inner join equipamiento as e on e.id=me.equipo_id "
				+ ((!whereParams.isEmpty()) ? " where " + String.join(" and ", whereParams) : "")
				+ ((!selectParams.isEmpty()) ? " group by" + String.join(",", groupParams) : "");

		String sql3 = sql1 + " order by consumo offset " + pageable.getPageNumber() * pageable.getPageSize()
				+ " rows fetch next " + pageable.getPageSize() + " rows only";

		Query query = entityManager.createNativeQuery(sql3);
		List<Object> resultList = query.getResultList();

		List<Object> table = new ArrayList<>();
		table.add(columns);
		if (resultList.size() == 1) {
			Object object = resultList.get(0);
			if (object != null && object.getClass().isArray()) {
				table.add(resultList);
			} else {
				ArrayList<List<Object>> data = new ArrayList<>();
				data.add(new ArrayList<>(resultList));
				table.add(data);
			}
		} else {
			table.addAll(resultList);
		}
		Page<Object> page = new PageImpl<Object>(table, pageable, table.size());
		PageDTO<Object> pageDTO = new PageDTO<>();
		pageDTO.setSize(page.getSize());
		pageDTO.setPage(page.getNumber());
		pageDTO.setTotalItems(page.getTotalElements());
		pageDTO.setTotalPages(page.getTotalPages());
		pageDTO.setResults(page.getContent());
		return pageDTO;
	}
}
