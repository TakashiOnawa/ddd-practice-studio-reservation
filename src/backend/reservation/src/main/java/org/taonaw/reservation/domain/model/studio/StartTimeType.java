package org.taonaw.reservation.domain.model.studio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.time.LocalTime;
import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StartTimeType {
    ON_THE_HOUR(0),
    ON_THE_HALF_HOUR(30);

    private final int startMinutes;

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
