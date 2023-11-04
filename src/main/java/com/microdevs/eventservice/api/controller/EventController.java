package com.microdevs.eventservice.api.controller;

import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.internal.dto.EventDto;
import com.microdevs.eventservice.internal.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "event")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody CreateEventDto createEventDto) {
        EventDto eventDto = service.createEvent(createEventDto);
        return ResponseEntity.ok(eventDto);
    }

}
