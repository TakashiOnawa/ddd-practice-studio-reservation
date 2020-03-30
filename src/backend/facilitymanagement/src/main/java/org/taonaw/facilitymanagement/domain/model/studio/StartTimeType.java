package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StartTimeType {
    ON_THE_HOUR(1, 0),
    ON_THE_HALF_HOUR(2, 30);

    private final int value;
    private final int startMinutes;

    public static StartTimeType from(int value) {
        for (var item : values()) {
            if (item.startMinutes == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("定義されていません。");
    }
}
