package com.microdevs.eventservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageUtil {
    private static final String EVENT_NOT_FOUND_WITH_ID = "No event record found for id number %d";

    public static String getMessageDetail(Long id) {
        return String.format(EVENT_NOT_FOUND_WITH_ID, id);
    }
}
