package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
public class DateOfBirth {
    private final LocalDate value;

    public DateOfBirth(@NonNull LocalDate value) {
        this.value = value;
    }
}
