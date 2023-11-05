package com.microdevs.eventservice.internal.dto;

import com.microdevs.baseservice.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventDto implements Serializable {
    private Long id;
    private String organizationName;
    private String eventName;
    private String location;
    private StatusType status;
    private LocalDateTime eventDate;
}
