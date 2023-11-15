package com.microdevs.eventservice.internal.service;

import com.microdevs.eventservice.integration.dto.EventDetailDto;

public interface EventDetailService {
    void saveEventDetail(EventDetailDto eventDetailDto);
}
