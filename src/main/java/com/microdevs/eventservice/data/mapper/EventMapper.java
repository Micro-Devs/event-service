package com.microdevs.eventservice.data.mapper;

import com.microdevs.baseservice.enums.StatusType;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.api.request.UpdateEventDto;
import com.microdevs.eventservice.data.entity.Event;
import com.microdevs.eventservice.internal.dto.EventDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EventMapper {

    @Mapping(source = "status", target = "status")
    public abstract Event toCreateEntity(CreateEventDto createEventDto, StatusType status);

    public abstract EventDto toDto(Event event);

    public abstract Event updateEvent(@MappingTarget Event event, UpdateEventDto updateEventDto);

    public abstract List<EventDto> toEventDtoList(List<Event> eventList);

    public Page<EventDto> mapEventPageToEventDTOPage(Page<Event> eventPage, @Context Pageable pageable) {
        List<EventDto> dtoList = toEventDtoList(eventPage.getContent());
        return new PageImpl<>(dtoList, pageable, eventPage.getTotalElements());
    }

    public abstract Event toEntity(EventDto eventDto);

    public abstract List<EventDto> toEntityList(List<Event> events);
}
