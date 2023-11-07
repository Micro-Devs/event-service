package com.microdevs.eventservice.data.service;

import com.microdevs.baseservice.exception.EventNotFoundException;
import com.microdevs.eventservice.api.filter.FilterEvent;
import com.microdevs.eventservice.data.entity.Event;
import com.microdevs.eventservice.data.mapper.EventMapper;
import com.microdevs.eventservice.data.repository.EventRepository;
import com.microdevs.eventservice.internal.EventSpecification;
import com.microdevs.eventservice.internal.dto.EventDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventDataServiceTest {
    @Mock
    private EventRepository mockRepository;
    @Mock
    private EventMapper mockMapper;
    @InjectMocks
    private EventDataService service;

    @Test
    public void given_id_should_returnEventDto() {
        Long id = 1L;

        Event event = new Event();
        event.setEventName("test");
        event.setId(id);
        Optional<Event> optionalEvent = Optional.of(event);

        EventDto expected = new EventDto();
        expected.setEventName("test");
        expected.setId(id);

        when(mockRepository.findById(anyLong())).thenReturn(optionalEvent);
        when(mockMapper.toDto(any(Event.class))).thenReturn(expected);

        EventDto actual = service.getEventDtoById(id);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEventName(), actual.getEventName());

        verify(mockRepository, times(1)).findById(anyLong());
        verify(mockMapper, times(1)).toDto(any(Event.class));
    }

    @Test
    public void when_eventIsNull_should_throwEventNotFoundException() {
        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        Executable executable = () -> service.getEventDtoById(1L);

        assertThrows(EventNotFoundException.class, executable);

        verify(mockRepository, times(1)).findById(anyLong());
    }

    @Test
    public void given_specAndPageable_should_returnPageEventDto() {
        FilterEvent filterEvent = new FilterEvent();
        filterEvent.setEventName("test");
        Specification<Event> spec = EventSpecification.eventWithFilters(filterEvent);
        Pageable pageable = Pageable.ofSize(1);

        List<Event> content = new ArrayList<>();
        Event event = new Event();
        content.add(event);
        Page<Event> page = new PageImpl<>(content, PageRequest.of(1, 1), content.size());

        List<EventDto> expectedContent = new ArrayList<>();
        EventDto eventDto = new EventDto();
        expectedContent.add(eventDto);
        Page<EventDto> expected = new PageImpl<>(expectedContent, PageRequest.of(1, 1), expectedContent.size());

        when(mockRepository.findAll(ArgumentMatchers.<Specification<Event>>any(), any(Pageable.class))).thenReturn(page);
        when(mockMapper.mapEventPageToEventDTOPage(any(), any())).thenReturn(expected);

        Page<EventDto> actual = service.getEventsWithSpec(spec, pageable);

        assertEquals(actual.getTotalPages(), expected.getTotalPages());
        assertEquals(actual.getContent().get(0).getEventName(), expected.getContent().get(0).getEventName());
    }

}