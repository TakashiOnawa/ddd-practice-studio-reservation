package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
public class DateOfBirth {
    private final LocalDate value;

    public DateOfBirth(LocalDate value) {
        Assertion.required(value);
        this.value = value;
    }
}
