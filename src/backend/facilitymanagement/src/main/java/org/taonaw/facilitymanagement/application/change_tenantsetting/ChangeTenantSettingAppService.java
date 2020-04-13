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

    public ChangeTenantSettingResult handle(ChangeTenantSettingCommand command) {
        var tenantSetting = tenantSettingRepository.get();

        tenantSetting.changeTenantName(new TenantName(command.getTenantName()));
        tenantSetting.changeOpeningHours(new OpeningHours(command.getOpeningStartTime(), command.getOpeningEndTime()));
        tenantSetting.changePersonalPracticeMaxNumberOfUsers(new MaxNumberOfUsers(command.getPersonalPracticeMaxNumberOfUsers()));
        tenantSetting.changeReservationStartDateTime(PracticeType.BAND, new ReservationStartDateTime(
                        command.getBandPracticeReservationStartDateValue(),
                        ReservationStartDateType.from(command.getBandPracticeReservationStartDateType()),
                        command.getBandPracticeReservationStartHour()));
        tenantSetting.changeReservationStartDateTime(PracticeType.PERSONAL, new ReservationStartDateTime(
                command.getPersonalPracticeReservationStartDateValue(),
                ReservationStartDateType.from(command.getPersonalPracticeReservationStartDateType()),
                command.getPersonalPracticeReservationStartHour()));

        tenantSettingRepository.update(tenantSetting);

        return ChangeTenantSettingResult.builder()
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
