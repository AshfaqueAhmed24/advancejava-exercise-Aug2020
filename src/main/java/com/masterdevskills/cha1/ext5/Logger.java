package com.masterdevskills.cha1.ext5;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Supplier;

//TODO: implement info, trace, debug, warn
public class Logger implements Log {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final String DELIM = "{}";
    private volatile boolean enabled;

    enum LogTag {
        INFO, TRACE, DEBUG, WARN
    }

    private Logger() {
    }

    public static Log getLogger() {
        return new Logger();
    }

    @Override
    public boolean isLoggable() {
        return enabled;
    }

    @Override
    public void enableLogging() {
        this.enabled = true;
    }

    @Override
    public void info(final String message, final Object... params) {
        if (isLoggable()) {
            System.out.println(getLogPrefix(LogTag.INFO) + formatMessage(message, params));
        }
    }

    private String formatMessage(final String message, final Object[] params) {
        if (message != null && params != null) {
            StringBuilder sbMessage = new StringBuilder(message);

            for (Object arg : params) {
                int index = sbMessage.indexOf(DELIM);
                if (index == -1) {
                    break;
                }
                sbMessage.replace(index, index + DELIM.length(), arg == null ? "null" : arg.toString());
            }

            return sbMessage.toString();
        }
        return message;
    }

    @Override
    public void info(final String message, final Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(getLogPrefix(LogTag.INFO) + formatMessage(message, params.get()));
        }
    }

    @Override
    public void trace(final String message, final Object... params) {
        if (isLoggable()) {
            System.out.println(getLogPrefix(LogTag.TRACE) + formatMessage(message, params));
        }
    }

    @Override
    public void trace(final String message, final Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(getLogPrefix(LogTag.TRACE) + formatMessage(message, params.get()));
        }
    }

    @Override
    public void debug(final String message, final Object... params) {
        if (isLoggable()) {
            System.out.println(getLogPrefix(LogTag.DEBUG) + formatMessage(message, params));
        }
    }

    @Override
    public void debug(final String message, final Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(getLogPrefix(LogTag.DEBUG) + formatMessage(message, params.get()));
        }
    }

    @Override
    public void warn(final String message, final Object... params) {
        if (isLoggable()) {
            System.out.println(getLogPrefix(LogTag.WARN) + formatMessage(message, params));
        }
    }

    @Override
    public void warn(final String message, final Supplier<Object[]> params) {
        if (isLoggable()) {
            System.out.println(getLogPrefix(LogTag.WARN) + formatMessage(message, params.get()));
        }
    }

    public Logger setEnabled(final boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    private String getLogPrefix(LogTag logTag) {

        return getFormattedCurrentTime() + " " + logTag.name() + " ";
    }

    private String getFormattedCurrentTime() {

        return sdf.format(new Date());
    }
}


