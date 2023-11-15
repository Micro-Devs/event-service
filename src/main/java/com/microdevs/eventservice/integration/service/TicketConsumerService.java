package com.microdevs.eventservice.integration.service;

import com.microdevs.eventservice.integration.dto.EventDetailDto;
import com.microdevs.eventservice.internal.service.EventDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketConsumerService {

    private final EventDetailService service;
    @KafkaListener(topics = "ticket-activity", containerFactory = "ticketConsumerFactory")
    public void listenTicketService(EventDetailDto eventDetail) {
        service.saveEventDetail(eventDetail);
    }
}
