package com.microdevs.eventservice.data.service;

import com.microdevs.eventservice.data.mapper.EventDetailMapper;
import com.microdevs.eventservice.data.repository.EventDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class EventDetailDataService {
    private final EventDetailRepository repository;
    private final EventDetailMapper mapper;

    public EventDetailDataService(EventDetailRepository repository, EventDetailMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
