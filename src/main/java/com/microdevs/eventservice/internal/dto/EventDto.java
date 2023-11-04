package com.microdevs.eventservice.internal.dto;

import com.microdevs.eventservice.enums.EventStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventDto implements Serializable {
    private String organizationName;
    private String eventName;
    private String location;
    private EventStatus status;
    private LocalDateTime eventDate;
}
