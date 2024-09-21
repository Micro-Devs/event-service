package com.microdevs.eventservice.data.mapper;

import com.microdevs.eventservice.data.entity.EventDetail;
import com.microdevs.eventservice.integration.dto.EventDetailDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EventDetailMapper {
    public abstract EventDetail toEntity(EventDetailDto eventDetailDto);
}
