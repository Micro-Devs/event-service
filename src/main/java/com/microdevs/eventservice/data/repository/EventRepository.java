package com.microdevs.eventservice.data.repository;

import com.microdevs.eventservice.data.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    boolean existsByOrganizationNameAndEventNameAndEventDate(String organizationName, String eventName, LocalDateTime eventDate);
}
