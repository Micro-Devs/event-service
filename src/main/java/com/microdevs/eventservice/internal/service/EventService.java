package com.microdevs.eventservice.internal.service;

import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.internal.dto.EventDto;

public interface EventService {
    EventDto createEvent(CreateEventDto createEventDto);
}
