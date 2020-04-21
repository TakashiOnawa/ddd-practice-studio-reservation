package org.taonaw.facilitymanagement.application.query.reservationsetting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class ReservationSettingDto {
    @NonNull private String studioId;
    private int practiceType;
    private int startTimeType;
    private int maxNumberOfUsers;
    private boolean isNumberOfUsersUnLimited;
    private int reservationStartDateValue;
    private int reservationStartDateType;
    private int reservationStartHour;
    @NonNull private LocalTime openingStartTime;
    @NonNull private LocalTime openingEndTime;
}
