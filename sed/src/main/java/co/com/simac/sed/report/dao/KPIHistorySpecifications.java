package co.com.simac.sed.report.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import co.com.simac.sed.kpiindicatormanagement.model.KPIIndicator;
import co.com.simac.sed.report.model.KPIHistory;

public class KPIHistorySpecifications {

	public static Specification<KPIHistory> filter(final Long idKPIIndicator, final Long idVariable,
			final Date initialDate, final Date finalDate) {

		return new Specification<KPIHistory>() {
			@Override
			public Predicate toPredicate(Root<KPIHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<>();
				Join<KPIHistory, KPIIndicator> variableJoin = root.join("kpiindicator");

				if (initialDate != null && !StringUtils.isEmpty(initialDate)) {
					Predicate initDatePredicate = cb.greaterThanOrEqualTo(root.<Date>get("startDate"), initialDate);
					predicates.add(initDatePredicate);
				}

				if (finalDate != null && !StringUtils.isEmpty(finalDate)) {
					Predicate initDatePredicate = cb.lessThanOrEqualTo(root.<Date>get("startDate"), finalDate);
					predicates.add(initDatePredicate);
				}

				if (idKPIIndicator != -1) {
					Predicate kpiPredicate = cb.equal(root.get("kpiindicator"), idKPIIndicator);
					predicates.add(kpiPredicate);
				}

				if (idVariable != -1) {
					Predicate variablePredicate = cb.equal(variableJoin.get("variable"), idVariable);
					predicates.add(variablePredicate);
				}

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));

			}

		};
	}

}
