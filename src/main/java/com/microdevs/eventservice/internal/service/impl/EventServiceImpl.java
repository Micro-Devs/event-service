package com.microdevs.eventservice.internal.service.impl;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.eventservice.api.filter.FilterEvent;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.api.request.UpdateEventDto;
import com.microdevs.eventservice.data.entity.Event;
import com.microdevs.eventservice.data.service.EventDataService;
import com.microdevs.eventservice.exception.EventAlreadyExistsException;
import com.microdevs.eventservice.exception.EventDeletedException;
import com.microdevs.eventservice.internal.EventSpecification;
import com.microdevs.eventservice.internal.dto.EventDto;
import com.microdevs.eventservice.internal.service.EventService;
import com.microdevs.eventservice.util.ExceptionUtil;
import com.microdevs.eventservice.util.MessageUtil;
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
        boolean checkUniqueEvent = eventDataService.checkUniqueEvent(createEventDto);

        if (checkUniqueEvent) {
            throw new EventAlreadyExistsException(ExceptionUtil.EVENT_ALREADY_EXISTS.getMessage()
                    , ExceptionUtil.EVENT_ALREADY_EXISTS.getCode(), MessageUtil.EVENT_ALREADY_EXISTS);
        }

        return eventDataService.saveNewEvent(createEventDto);
    }

    @Override
    public EventDto updateEvent(Long id, UpdateEventDto updateEventDto) {
        EventDto eventDto = eventDataService.getEventDtoById(id);
        checkTerminateStatus(eventDto);
        return eventDataService.updateEvent(eventDto, updateEventDto);
    }

    @Override
    public EventDto updateStatusSuspend(Long id) {
        EventDto eventDto = eventDataService.getEventDtoById(id);
        checkTerminateStatus(eventDto);
        eventDto.setStatus(StatusType.SUSPEND);
        return eventDataService.saveEvent(eventDto);
    }

    @Override
    public EventDto updateStatusActive(Long id) {
        EventDto eventDto = eventDataService.getEventDtoById(id);
        checkTerminateStatus(eventDto);
        eventDto.setStatus(StatusType.ACTIVE);
        return eventDataService.saveEvent(eventDto);
    }

    @Override
    public void deleteEvent(Long id) {
        EventDto eventDto = eventDataService.getEventDtoById(id);
        eventDto.setStatus(StatusType.TERMINATED);
        eventDataService.saveEvent(eventDto);
    }

    @Override
    public Page<EventDto> findEvents(FilterEvent filter, Pageable pageable) {
        Specification<Event> spec = EventSpecification.eventWithFilters(filter);
        return eventDataService.getEventsWithSpec(spec, pageable);
    }

    private void checkTerminateStatus(EventDto eventDto) {
        if (eventDto.getStatus().equals(StatusType.TERMINATED)) {
            throw new EventDeletedException(ExceptionUtil.EVENT_DELETED.getMessage(), ExceptionUtil.EVENT_DELETED.getCode()
                    , MessageUtil.getMessageDetail(MessageUtil.EVENT_IS_DELETED));
        }
    }
}
