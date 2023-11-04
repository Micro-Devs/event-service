package com.microdevs.eventservice.api.controller;

import com.microdevs.eventservice.internal.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }
}
