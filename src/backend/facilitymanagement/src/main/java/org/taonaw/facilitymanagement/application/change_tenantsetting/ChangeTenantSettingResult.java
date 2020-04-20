package org.taonaw.facilitymanagement.application.change_tenantsetting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.PracticeType;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.TenantSetting;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class ChangeTenantSettingResult {
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

    static ChangeTenantSettingResult of(TenantSetting tenantSetting) {
        return builder()
                .tenantName(tenantSetting.getTenantName().getValue())
                .openingStartTime(tenantSetting.getOpeningHours().getStart())
                .openingEndTime(tenantSetting.getOpeningHours().getEnd())
                .personalPracticeMaxNumberOfUsers(tenantSetting.getPersonalPracticeMaxNumberOfUsers().getValue())
                .bandPracticeReservationStartDateValue(tenantSetting.getReservationStartDateTime(PracticeType.BAND).getStartDateValue())
                .bandPracticeReservationStartDateType(tenantSetting.getReservationStartDateTime(PracticeType.BAND).getStartDateType().getValue())
                .bandPracticeReservationStartHour(tenantSetting.getReservationStartDateTime(PracticeType.BAND).getStartHour())
                .personalPracticeReservationStartDateValue(tenantSetting.getReservationStartDateTime(PracticeType.PERSONAL).getStartDateValue())
                .personalPracticeReservationStartDateType(tenantSetting.getReservationStartDateTime(PracticeType.PERSONAL).getStartDateType().getValue())
                .personalPracticeReservationStartHour(tenantSetting.getReservationStartDateTime(PracticeType.PERSONAL).getStartHour())
                .build();
    }
}
