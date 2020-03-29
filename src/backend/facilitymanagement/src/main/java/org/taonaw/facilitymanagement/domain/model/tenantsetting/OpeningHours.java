package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalTime;

@Getter
@EqualsAndHashCode
public class OpeningHours {
    public static final OpeningHours ALL_DAY;

    private final LocalTime start;
    private final LocalTime end;

    public OpeningHours(@NonNull LocalTime start, @NonNull LocalTime end) {
        this.start = start;
        this.end = end;
    }

    static {
        ALL_DAY = new OpeningHours(LocalTime.MIN, LocalTime.MIN);
    }
}
