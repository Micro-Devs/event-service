package com.microdevs.eventservice.internal;

import com.microdevs.eventservice.api.filter.FilterEvent;
import com.microdevs.eventservice.data.entity.Event;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EventSpecification {

    public static Specification<Event> eventWithFilters(FilterEvent filter) {
        return (Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // ID kontrolü
            if (filter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
            }

            // Organization Name kontrolü
            if (filter.getOrganizationName() != null && !filter.getOrganizationName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("organizationName"), filter.getOrganizationName()));
            }

            // Event Name kontrolü
            if (filter.getEventName() != null && !filter.getEventName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("eventName"), filter.getEventName()));
            }

            // Start Time kontrolü
            if (filter.getStartTime() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("eventDate"), filter.getStartTime()));
            }

            // End Time kontrolü
            if (filter.getEndTime() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("eventDate"), filter.getEndTime()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
