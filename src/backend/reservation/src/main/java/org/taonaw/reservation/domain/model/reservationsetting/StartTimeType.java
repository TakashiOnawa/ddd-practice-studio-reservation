package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.time.LocalTime;
import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StartTimeType {
    ON_THE_HOUR(1,0),
    ON_THE_HALF_HOUR(2, 30);

    private final int value;
    private final int startMinutes;

    public static StartTimeType from(int value) {
        for (var item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("定義されていません。");
    }

    public boolean isSatisfiedBy(@NonNull UseTime useTime) {
        return useTime.isStartMinutesEquals(startMinutes);
    }

    public static void validateStartTime(@NonNull LocalTime startTime) {
        var minutes = startTime.getMinute();
        if (Arrays.stream(values()).allMatch(value -> value.startMinutes == minutes)) {
            return;
        }
        throw new IllegalArgumentException("スタート時間は 0 分か 30 分である必要があります。");
    }
}
