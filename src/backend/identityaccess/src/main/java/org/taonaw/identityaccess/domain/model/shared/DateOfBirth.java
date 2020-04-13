package org.taonaw.identityaccess.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@EqualsAndHashCode
public class DateOfBirth {
    private final LocalDate value;

    public DateOfBirth(@NonNull LocalDate value) {
        this.value = value;
    }

    public DateOfBirth(@NonNull String value, @NonNull String pattern) {
        this.value = LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
    }
}
