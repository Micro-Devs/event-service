package com.microdevs.eventservice.integration.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EventDetailDto {
    private UUID guid;
    private Long eventId;
    private Long userId;
}
