package com.microdevs.eventservice.util;

import lombok.Getter;

@Getter
public enum ExceptionUtil {

    EVENT_NOT_FOUND(700, "Error not found"),
    EVENT_DELETED(701, "Event deleted");

    private final int code;
    private final String message;

    ExceptionUtil(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
