package com.microdevs.eventservice.internal.service.impl;

import com.microdevs.eventservice.data.service.EventDetailDataService;
import com.microdevs.eventservice.integration.dto.EventDetailDto;
import com.microdevs.eventservice.internal.service.EventDetailService;
import org.springframework.stereotype.Service;

@Service
public class EventDetailServiceImpl implements EventDetailService {
    private final EventDetailDataService eventDetailDataService;

    public EventDetailServiceImpl(EventDetailDataService eventDetailDataService) {
        this.eventDetailDataService = eventDetailDataService;
    }

    @Override
    public void saveEventDetail(EventDetailDto eventDetailDto) {
        eventDetailDataService.save(eventDetailDto);
    }
}
