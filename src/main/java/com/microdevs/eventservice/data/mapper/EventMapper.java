package com.microdevs.eventservice.data.mapper;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.data.entity.Event;
import com.microdevs.eventservice.internal.dto.EventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EventMapper {

    @Mapping(source = "status", target = "status")
    public abstract Event toEntity(CreateEventDto createEventDto, StatusType status);

    public abstract EventDto toDto(Event savedEvent);
}
