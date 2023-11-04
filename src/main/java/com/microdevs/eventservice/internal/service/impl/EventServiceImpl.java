package com.microdevs.eventservice.internal.service.impl;

import com.microdevs.eventservice.data.service.EventDataService;
import com.microdevs.eventservice.internal.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private final EventDataService eventDataService;

    public EventServiceImpl(EventDataService eventDataService) {
        this.eventDataService = eventDataService;
    }
}
