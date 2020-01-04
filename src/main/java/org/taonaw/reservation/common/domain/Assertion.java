package org.taonaw.reservation.common.domain;

import org.apache.commons.lang3.StringUtils;

public final class Assertion {
    public static void argumentNotEmpty(String value, String message) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void argumentRange(String value, int min, int max) {
        argumentRange(value, min ,max, null);
    }

    public static void argumentRange(String value, int min, int max, String message) {
        String exceptionMessage = message;
        if (StringUtils.isEmpty(message)) {
            exceptionMessage = String.format("%d 文字以上 %d 文字以下でなければなりません。", min ,max);
        }
        if (value == null || value.length() < min || max < value.length()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static void argumentRange(int value, int min, int max) {
        argumentRange(value, min, max, null);
    }

    public static void argumentRange(int value, int min, int max, String message) {
        String exceptionMessage = message;
        if (StringUtils.isEmpty(message)) {
            exceptionMessage = String.format("%d 以上 %d 以下でなければなりません。", min ,max);
        }
        if (value < min || max < value) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static void argumentMin(int value, int min) {
        argumentMin(value, min, null);
    }

    public static void argumentMin(int value, int min, String message) {
        String exceptionMessage = message;
        if (StringUtils.isEmpty(message)) {
            exceptionMessage = String.format("%d 以上でなければなりません。", min);
        }
        if (value < min) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}
