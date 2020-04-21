package org.taonaw.facilitymanagement.application.command.change_tenantsetting;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.*;

import java.util.stream.Collectors;

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
        tenantSetting.changeCancellationFeeRates(command.getCancellationFeeRates().stream()
                .map(item -> new CancellationFeeRate(item.getDaysAgo(), item.getRate()))
                .collect(Collectors.toSet()));

        tenantSettingRepository.update(tenantSetting);

        return ChangeTenantSettingResult.of(tenantSetting);
    }
}
