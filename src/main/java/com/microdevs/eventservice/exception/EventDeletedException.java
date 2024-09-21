package com.microdevs.eventservice.exception;

import com.microdevs.baseservice.exception.MicroException;

public class EventDeletedException extends MicroException {
    public EventDeletedException(String message, Integer errorCode, String messageDetail) {
        super(message, errorCode, messageDetail);
    }
}
