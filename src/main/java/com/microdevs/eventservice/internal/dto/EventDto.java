package com.microdevs.eventservice.internal.dto;

import com.microdevs.eventservice.enums.EventStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDto {
    private String organizationName;
    private String eventName;
    private String location;
    private EventStatus status;
    private LocalDateTime eventDate;
}
