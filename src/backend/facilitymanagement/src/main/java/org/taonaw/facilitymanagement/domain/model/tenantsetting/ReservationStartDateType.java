package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReservationStartDateType {
    DAYS_AGO(1),
    WEEKS_AGO(2),
    MONTHS_AGO(3);

    private final int value;

    public static ReservationStartDateType from(int value) {
        for (var item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("定義されていません。");
    }
}
