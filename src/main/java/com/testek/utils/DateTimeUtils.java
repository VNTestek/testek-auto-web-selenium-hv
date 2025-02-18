package com.testek.utils;

import java.time.Instant;

public class DateTimeUtils {
    public static long getCurrentTimestamp() {
        return Instant.now().toEpochMilli();
    }
}
