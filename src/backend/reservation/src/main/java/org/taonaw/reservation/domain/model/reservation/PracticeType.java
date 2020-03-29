package org.taonaw.reservation.domain.model.reservation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PracticeType {
    BAND(1),
    PERSONAL(2);

    private final int value;

    public static PracticeType from(int value) {
        for (var item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("定義されていません。");
    }
}
