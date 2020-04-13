package org.taonaw.managementsite.application.facilitymanagement.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TenantSettingDto {
    private String tenantName;
    private LocalTime openingStartTime;
    private LocalTime openingEndTime;
    private int personalPracticeMaxNumberOfUsers;
    private int personalPracticeReservationStartDateValue;
    private int personalPracticeReservationStartDateType;
    private int personalPracticeReservationStartHour;
    private int bandPracticeReservationStartDateValue;
    private int bandPracticeReservationStartDateType;
    private int bandPracticeReservationStartHour;
}
