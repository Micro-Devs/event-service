package com.microdevs.eventservice.integration.service;

import com.microdevs.eventservice.integration.dto.EventDetailDto;
import com.microdevs.eventservice.internal.service.EventDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketConsumerService {

    private final EventDetailService service;

    @KafkaListener(topics = "ticket-activity")
    public void listenTicketService(@Payload EventDetailDto eventDetail) {
        service.saveEventDetail(eventDetail);
    }
}
