package org.taonaw.studio_reservation.domain.model.practiceTypeSetting;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public enum ReservationStartDateTypes {
    DAYS_AGO(1) {
        @Override
        public LocalDate startDate(@NonNull LocalDateTime dateTime, int startDateValue) {
            return dateTime.minusDays(startDateValue).toLocalDate();
        }
    },
    WEEKS_AGO(2) {
        @Override
        public LocalDate startDate(@NonNull LocalDateTime dateTime, int startDateValue) {
            return dateTime.minusDays(startDateValue * 7).toLocalDate();
        }
    },
    MONTHS_AGO(3) {
        @Override
        public LocalDate startDate(@NonNull LocalDateTime dateTime, int startDateValue) {
            return dateTime.minusMonths(startDateValue).toLocalDate();
        }
    };

    private final int value;

    ReservationStartDateTypes(int value) {
        this.value = value;
    }

    public static ReservationStartDateTypes of(int value) {
        for (var item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("定義されていません。");
    }

    public abstract LocalDate startDate(LocalDateTime dateTime, int startDateValue);
}
