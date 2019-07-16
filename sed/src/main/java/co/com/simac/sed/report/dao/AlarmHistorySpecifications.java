package co.com.simac.sed.report.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import co.com.simac.sed.systemalarms.model.AlarmHistory;

public class AlarmHistorySpecifications {

	public static Specification<AlarmHistory> filter(final Long idFailureCategory, final Long idKpiAlarm,
			final Date initialDate, final Date finalDate, final int status) {

		return new Specification<AlarmHistory>() {
			@Override
			public Predicate toPredicate(Root<AlarmHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<>();

				if (initialDate != null && !StringUtils.isEmpty(initialDate)) {
					Predicate initDatePredicate = cb.greaterThanOrEqualTo(root.<Date>get("startDate"), initialDate);
					predicates.add(initDatePredicate);
				}

				if (finalDate != null && !StringUtils.isEmpty(finalDate)) {
					Predicate initDatePredicate = cb.lessThanOrEqualTo(root.<Date>get("startDate"), finalDate);
					predicates.add(initDatePredicate);
				}

				if (idFailureCategory != -1) {
					Predicate kpiPredicate = cb.equal(root.get("failureCategory"), idFailureCategory);
					predicates.add(kpiPredicate);
				}

				if (idKpiAlarm != -1) {
					Predicate kpiPredicate = cb.equal(root.get("kpiIndicatorAlarm"), idKpiAlarm);
					predicates.add(kpiPredicate);
				}

				if (status != -1) {
					Predicate kpiPredicate = null;
					if (status == 1) {
						kpiPredicate = cb.equal(root.get("status"), true);
					} else if (status == 0) {
						kpiPredicate = cb.equal(root.get("status"), false);
					}

					if (kpiPredicate != null) {
						predicates.add(kpiPredicate);
					}
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}

}
