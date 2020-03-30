package org.taonaw.facilitymanagement.query.tenantsetting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class TenantSettingDto {
    @NonNull private String tenantName;
    @NonNull private LocalTime openingStartTime;
    @NonNull private LocalTime openingEndTime;
    private int personalPracticeMaxNumberOfUsers;
    private int personalPracticeReservationStartDateValue;
    private int personalPracticeReservationStartDateType;
    private int personalPracticeReservationStartHour;
    private int bandPracticeReservationStartDateValue;
    private int bandPracticeReservationStartDateType;
    private int bandPracticeReservationStartHour;
}
