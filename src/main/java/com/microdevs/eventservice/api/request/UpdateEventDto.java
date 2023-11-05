package com.microdevs.eventservice.api.request;

import com.microdevs.baseservice.annotation.ValidLocalDateTimeFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateEventDto implements Serializable {
    @NotNull(message = "'organizationName' must not be empty")
    @Size(min = 8, max = 50, message = "size should be between 8 and 50")
    @Pattern(regexp = "^[A-Za-z]+$", message = "'organizationName' must consist of characters only")
    private String organizationName;

    @NotNull(message = "eventName must not be empty")
    @Size(min = 5, max = 30, message = "size should be between 8 and 50")
    @Pattern(regexp = "^[A-Za-z]+$", message = "'eventName' must consist of characters only")
    private String eventName;

    @NotNull(message = "location must not be empty")
    @Size(min = 10, max = 100, message = "size should be between 8 and 50")
    private String location;

    @NotNull(message = "eventDate must not be empty")
    @Future(message = "Expected date must be in the future")
    @ValidLocalDateTimeFormat
    private LocalDateTime eventDate;
}
