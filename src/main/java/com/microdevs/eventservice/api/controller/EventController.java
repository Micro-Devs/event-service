package com.microdevs.eventservice.api.controller;

import com.microdevs.eventservice.api.filter.FilterEvent;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.api.request.UpdateEventDto;
import com.microdevs.eventservice.internal.dto.EventDto;
import com.microdevs.eventservice.internal.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        EventDto response = service.createEvent(createEventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable(value = "id") Long id, @Valid @RequestBody UpdateEventDto updateEventDto) {
        return service.updateEvent(id, updateEventDto);
    }

    @PutMapping("/{id}/status/suspend")
    public EventDto updateStatusSuspend(@PathVariable(value = "id") Long id) {
        return service.updateStatusSuspend(id);
    }

    @PutMapping("/{id}/status/active")
    public EventDto updateStatusActive(@PathVariable(value = "id") Long id) {
        return service.updateStatusActive(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable(value = "id") Long id) {
        service.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public Page<EventDto> getEvents(FilterEvent filter, Pageable pageable) {
        return service.findEvents(filter, pageable);
        //deneme
    }

}
