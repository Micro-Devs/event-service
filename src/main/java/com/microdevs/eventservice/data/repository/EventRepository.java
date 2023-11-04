package com.microdevs.eventservice.data.repository;

import com.microdevs.eventservice.data.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
