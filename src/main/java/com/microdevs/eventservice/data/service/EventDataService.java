package com.microdevs.eventservice.data.service;

import com.microdevs.eventservice.data.mapper.EventMapper;
import com.microdevs.eventservice.data.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventDataService {

    private final EventRepository repository;
    private final EventMapper mapper;

    public EventDataService(EventRepository repository, EventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
