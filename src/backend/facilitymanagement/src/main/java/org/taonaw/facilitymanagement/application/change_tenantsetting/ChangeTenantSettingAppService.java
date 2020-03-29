package org.taonaw.facilitymanagement.application.change_tenantsetting;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.*;

@Service
@AllArgsConstructor
public class ChangeTenantSettingAppService {
    @Autowired
    private final ITenantSettingRepository tenantSettingRepository;

    public ChangeTenantSettingResponse handle(ChangeTenantSettingRequest request) {
        var tenantSetting = tenantSettingRepository.get();

        tenantSetting.changeTenantName(new TenantName(request.getTenantName()));
        tenantSetting.changeOpeningHours(new OpeningHours(request.getOpeningStartTime(), request.getOpeningEndTime()));
        tenantSetting.changePersonalPracticeMaxNumberOfUsers(new MaxNumberOfUsers(request.getPersonalPracticeMaxNumberOfUsers()));
        tenantSetting.changeReservationStartDateTime(PracticeType.BAND, new ReservationStartDateTime(
                        request.getBandPracticeReservationStartDateValue(),
                        ReservationStartDateType.from(request.getBandPracticeReservationStartDateType()),
                        request.getBandPracticeReservationStartHours()));
        tenantSetting.changeReservationStartDateTime(PracticeType.PERSONAL, new ReservationStartDateTime(
                request.getPersonalPracticeReservationStartDateValue(),
                ReservationStartDateType.from(request.getPersonalPracticeReservationStartDateType()),
                request.getPersonalPracticeReservationStartHours()));

        tenantSettingRepository.update(tenantSetting);

        return ChangeTenantSettingResponse.builder()
                .tenantName(tenantSetting.getTenantName().getValue())
                .openingStartTime(tenantSetting.getOpeningHours().getStart())
                .openingEndTime(tenantSetting.getOpeningHours().getEnd())
                .personalPracticeMaxNumberOfUsers(tenantSetting.getPersonalPracticeMaxNumberOfUsers().getValue())
                .bandPracticeReservationStartDateType(tenantSetting.getReservationStartDateTimeSetting().getBandPracticeValue().getStartDateValue())
                .bandPracticeReservationStartDateType(tenantSetting.getReservationStartDateTimeSetting().getBandPracticeValue().getStartDateType().getValue())
                .bandPracticeReservationStartHours(tenantSetting.getReservationStartDateTimeSetting().getBandPracticeValue().getStartHours())
                .personalPracticeReservationStartDateType(tenantSetting.getReservationStartDateTimeSetting().getPersonalPracticeValue().getStartDateValue())
                .personalPracticeReservationStartDateType(tenantSetting.getReservationStartDateTimeSetting().getPersonalPracticeValue().getStartDateType().getValue())
                .personalPracticeReservationStartHours(tenantSetting.getReservationStartDateTimeSetting().getPersonalPracticeValue().getStartHours())
                .build();
    }
}
