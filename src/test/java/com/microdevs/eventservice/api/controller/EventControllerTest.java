package com.microdevs.eventservice.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microdevs.eventservice.api.request.CreateEventDto;
import com.microdevs.eventservice.internal.dto.EventDto;
import com.microdevs.eventservice.internal.service.EventService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    private static final String URL = "/event";

    private MockMvc mockMvc;

    @Mock
    private EventService mockEventService;

    @InjectMocks
    private EventController eventController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(eventController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    @SneakyThrows
    public void given_createEventDto_should_returnHttpStatusIsCreated() {
        String string = "testtesttest";

        CreateEventDto createEventDto = new CreateEventDto();
        createEventDto.setEventName(string);
        createEventDto.setOrganizationName(string);
        createEventDto.setLocation(string);
        createEventDto.setEventDate(LocalDateTime.now().plusDays(1));

        EventDto response = new EventDto();
        response.setEventName(string);
        response.setOrganizationName(string);
        response.setLocation(string);
        response.setEventDate(LocalDateTime.now().plusDays(1L));

        Mockito.when(mockEventService.createEvent(Mockito.any(CreateEventDto.class))).thenReturn(response);

        mockMvc.perform(post(URL)
                        .content(objectMapper.writeValueAsBytes(createEventDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventName", is(response.getEventName())));
    }

}