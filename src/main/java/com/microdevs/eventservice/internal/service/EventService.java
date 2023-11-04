package com.microdevs.eventservice.internal.service;

import com.microdevs.eventservice.api.filter.FilterEvent;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.api.request.UpdateEventDto;
import com.microdevs.eventservice.internal.dto.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    EventDto createEvent(CreateEventDto createEventDto);

    EventDto updateEvent(Long id, UpdateEventDto updateEventDto);

    Page<EventDto> findEvent(FilterEvent filter, Pageable pageable);

    void deleteEvent(Long id);

    EventDto updateStatusSuspend(Long id);
}
