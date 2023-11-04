package com.microdevs.eventservice.api.filter;

import com.microdevs.baseservice.annotation.ValidLocalDateTimeFormat;
import com.microdevs.baseservice.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class FilterEvent {

    private Long id;

    @Size(min = 8, max = 50, message = "size should be between 8 and 50")
    @Pattern(regexp = "^[A-Za-z]+$", message = "'organizationName' must consist of characters only")
    private String organizationName;

    @Size(min = 5, max = 30, message = "size should be between 8 and 50")
    @Pattern(regexp = "^[A-Za-z]+$", message = "'eventName' must consist of characters only")
    private String eventName;

    private StatusType status;

    @ValidLocalDateTimeFormat
    private LocalDateTime startTime;

    @ValidLocalDateTimeFormat
    private LocalDateTime endTime;

}