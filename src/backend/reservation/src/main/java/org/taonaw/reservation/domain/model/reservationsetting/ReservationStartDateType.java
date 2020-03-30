package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReservationStartDateType {
    DAYS_AGO(1) {
        @Override
        public LocalDate getStartDate(LocalDateTime dateTime, int startDateValue) {
            return dateTime.minusDays(startDateValue).toLocalDate();
        }
    },
    WEEKS_AGO(2) {
        @Override
        public LocalDate getStartDate(LocalDateTime dateTime, int startDateValue) {
            return dateTime.minusDays(startDateValue * 7).toLocalDate();
        }
    },
    MONTHS_AGO(3) {
        @Override
        public LocalDate getStartDate(LocalDateTime dateTime, int startDateValue) {
            return dateTime.minusMonths(startDateValue).toLocalDate();
        }
    };

    private final int value;

    public static ReservationStartDateType from(int value) {
        for (var item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("定義されていません。");
    }

    public abstract LocalDate getStartDate(LocalDateTime dateTime, int startDateValue);
}
