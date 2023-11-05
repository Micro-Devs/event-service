package com.microdevs.eventservice.api.filter;

import com.microdevs.baseservice.annotation.EnumValidator;
import com.microdevs.baseservice.annotation.ValidLocalDateTimeFormat;
import com.microdevs.baseservice.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
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

    @EnumValidator(enumClass = StatusType.class)
    private StatusType status;

    @ValidLocalDateTimeFormat
    private LocalDateTime startTime;

    @ValidLocalDateTimeFormat
    private LocalDateTime endTime;

    @AssertTrue(message = "A deleted event cannot be viewed. StatusType must be ACTIVE or SUSPEND")
    public boolean statusIsValid() {
        return this.status.equals(StatusType.TERMINATED);
    }

}
