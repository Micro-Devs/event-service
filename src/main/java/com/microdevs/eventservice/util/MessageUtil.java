package com.microdevs.eventservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageUtil {
    public static final String EVENT_NOT_FOUND_WITH_ID = "No event record found for id number %s";
    public static final String EVENT_IS_DELETED = "This event has been deleted.";
    public static final String EVENT_ALREADY_EXISTS = "This event has been created before, the same event cannot be created on the same dates.";

    public static String getMessageDetail(String message, Object argument) {
        return String.format(message, argument.toString());
    }

    public static String getMessageDetail(String message) {
        return String.format(message);
    }
}
