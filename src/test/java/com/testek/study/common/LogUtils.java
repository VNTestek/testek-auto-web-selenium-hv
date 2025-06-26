package com.testek.study.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Generate a log message
 */
public class LogUtils {
    private static final Logger LOGGER = LogManager.getLogger("AutomationTest");

    public static void info(String message, Object... args) {
        LOGGER.info(message, args);
    }
    public static void info(String message, Throwable throwable, Object... args) {
        LOGGER.info(message, throwable, args);
    }
    public static void warn(String message, Object... args) {
        LOGGER.warn(message, args);
    }
    public static void error(String message, Object... args) {
        LOGGER.error(message, args);
    }
    public static void error(String message, Throwable throwable, Object... args) {
        LOGGER.error(message, throwable, args);
    }
    public static void fatal(String message, Object... args) {
        LOGGER.fatal(message, args);
    }
    public static void debug(String message, Object... args) {
        LOGGER.debug(message, args);
    }
}
