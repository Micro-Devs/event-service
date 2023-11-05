package com.microdevs.eventservice.internal.service.impl;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.eventservice.api.filter.FilterEvent;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.api.request.UpdateEventDto;
import com.microdevs.eventservice.data.entity.Event;
import com.microdevs.eventservice.data.service.EventDataService;
import com.microdevs.eventservice.internal.EventSpecification;
import com.microdevs.eventservice.internal.dto.EventDto;
import com.microdevs.eventservice.internal.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private final EventDataService eventDataService;

    public EventServiceImpl(EventDataService eventDataService) {
        this.eventDataService = eventDataService;
    }

    @Override
    public EventDto createEvent(CreateEventDto createEventDto) {
        return eventDataService.createEvent(createEventDto);
    }

    @Override
    public EventDto updateEvent(Long id, UpdateEventDto updateEventDto) {
        return eventDataService.updateEvent(id, updateEventDto);
    }

    @Override
    public EventDto updateStatusSuspend(Long id) {
        EventDto eventDto = eventDataService.getEventDtoById(id);
        eventDto.setStatus(StatusType.SUSPEND);
        return eventDataService.saveEvent(eventDto);
    }

    @Override
    public void deleteEvent(Long id) {
        EventDto eventDto = eventDataService.getEventDtoById(id);
        eventDto.setStatus(StatusType.TERMINATED);
        eventDataService.saveEvent(eventDto);
    }

    @Override
    public Page<EventDto> findEvent(FilterEvent filter, Pageable pageable) {
        Specification<Event> spec = EventSpecification.eventWithFilters(filter);
        return eventDataService.getEventWithSpec(spec, pageable);
    }
}
