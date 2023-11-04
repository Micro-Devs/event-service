package com.microdevs.eventservice.data.repository;

import com.microdevs.eventservice.data.entity.EventDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDetailRepository extends JpaRepository<EventDetail, Long> {
}
