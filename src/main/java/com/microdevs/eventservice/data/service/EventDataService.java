package com.microdevs.eventservice.data.service;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.data.entity.Event;
import com.microdevs.eventservice.data.mapper.EventMapper;
import com.microdevs.eventservice.data.repository.EventRepository;
import com.microdevs.eventservice.internal.dto.EventDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EventDataService {

    private final EventRepository repository;
    private final EventMapper mapper;

    public EventDataService(EventRepository repository, EventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public EventDto saveEvent(CreateEventDto createEventDto) {
        Event event = mapper.toEntity(createEventDto, StatusType.ACTIVE);
        Event savedEvent = repository.save(event);
        return mapper.toDto(savedEvent);
    }
}
