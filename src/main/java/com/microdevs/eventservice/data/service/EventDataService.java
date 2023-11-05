package com.microdevs.eventservice.data.service;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.baseservice.exception.EventNotFoundException;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.api.request.UpdateEventDto;
import com.microdevs.eventservice.data.entity.Event;
import com.microdevs.eventservice.data.mapper.EventMapper;
import com.microdevs.eventservice.data.repository.EventRepository;
import com.microdevs.eventservice.internal.dto.EventDto;
import com.microdevs.eventservice.util.ExceptionUtil;
import com.microdevs.eventservice.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public EventDto createEvent(CreateEventDto createEventDto) {
        Event event = mapper.toCreateEntity(createEventDto, StatusType.ACTIVE);
        Event savedEvent = repository.save(event);
        return mapper.toDto(savedEvent);
    }

    @Transactional
    public EventDto updateEvent(EventDto eventDto, UpdateEventDto updateEventDto) {
        Event event = mapper.toEntity(eventDto);
        Event mappedEvent = mapper.updateEvent(event, updateEventDto);
        Event savedEvent = repository.save(mappedEvent);
        return mapper.toDto(savedEvent);
    }

    @Transactional
    public Page<EventDto> getEventsWithSpec(Specification<Event> spec, Pageable pageable) {
        Page<Event> eventPage = repository.findAll(spec, pageable);
        return mapper.mapEventPageToEventDTOPage(eventPage, pageable);
    }

    @Transactional
    public EventDto getEventDtoById(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(ExceptionUtil.EVENT_NOT_FOUND.getMessage()
                        , ExceptionUtil.EVENT_NOT_FOUND.getCode()
                        , MessageUtil.getMessageDetail(MessageUtil.EVENT_NOT_FOUND_WITH_ID, id)));
        return mapper.toDto(event);
    }

    @Transactional
    public EventDto saveEvent(EventDto eventDto) {
        Event event = mapper.toEntity(eventDto);
        Event updatedEvent = repository.save(event);
        return mapper.toDto(updatedEvent);
    }

    private Event getEventById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(ExceptionUtil.EVENT_NOT_FOUND.getMessage()
                        , ExceptionUtil.EVENT_NOT_FOUND.getCode()
                        , MessageUtil.getMessageDetail(MessageUtil.EVENT_NOT_FOUND_WITH_ID, id)));
    }
}
