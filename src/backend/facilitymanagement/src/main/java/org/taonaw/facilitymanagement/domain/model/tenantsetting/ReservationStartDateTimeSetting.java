package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class ReservationStartDateTimeSetting {
    private final ReservationStartDateTime bandPracticeValue;
    private final ReservationStartDateTime personalPracticeValue;

    public ReservationStartDateTimeSetting(@NonNull ReservationStartDateTime bandPracticeValue,
                                           @NonNull ReservationStartDateTime personalPracticeValue) {
        this.bandPracticeValue = bandPracticeValue;
        this.personalPracticeValue = personalPracticeValue;
    }

    public ReservationStartDateTimeSetting change(@NonNull PracticeType practiceType,
                                                  @NonNull ReservationStartDateTime reservationStartDateTime) {
        if (practiceType.equals(PracticeType.BAND)) {
            return new ReservationStartDateTimeSetting(reservationStartDateTime, personalPracticeValue);
        } else if (practiceType.equals(PracticeType.PERSONAL)) {
            return new ReservationStartDateTimeSetting(bandPracticeValue, reservationStartDateTime);
        }
        throw new IllegalArgumentException("練習区分が定義されていません。");
    }
}
