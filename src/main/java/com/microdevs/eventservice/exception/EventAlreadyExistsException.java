package com.microdevs.eventservice.exception;

import com.microdevs.baseservice.exception.MicroException;

public class EventAlreadyExistsException extends MicroException {
    public EventAlreadyExistsException(String message, Integer errorCode, String messageDetail) {
        super(message, errorCode, messageDetail);
    }
}
