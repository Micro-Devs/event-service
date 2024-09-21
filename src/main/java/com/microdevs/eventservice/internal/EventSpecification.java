package com.microdevs.eventservice.internal;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.eventservice.api.filter.FilterEvent;
import com.microdevs.eventservice.data.entity.Event;
import com.microdevs.eventservice.exception.EventDeletedException;
import com.microdevs.eventservice.util.ExceptionUtil;
import com.microdevs.eventservice.util.MessageUtil;
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

            // Check ID
            if (filter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
            }

            // Check Organization Name
            if (filter.getOrganizationName() != null && !filter.getOrganizationName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("organizationName"), filter.getOrganizationName()));
            }

            // Check Event Name
            if (filter.getEventName() != null && !filter.getEventName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("eventName"), filter.getEventName()));
            }

            // Check Status
            if (filter.getStatus() != null && filter.getStatus() != StatusType.TERMINATED) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }

            if (filter.getStatus() != null && filter.getStatus() == StatusType.TERMINATED) {
                throw new EventDeletedException(ExceptionUtil.EVENT_DELETED.getMessage(), ExceptionUtil.EVENT_DELETED.getCode()
                        , MessageUtil.getMessageDetail(MessageUtil.EVENT_IS_DELETED));
            }

            // Check Start Time
            if (filter.getStartTime() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("eventDate"), filter.getStartTime()));
            }

            // Check End Time
            if (filter.getEndTime() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("eventDate"), filter.getEndTime()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
