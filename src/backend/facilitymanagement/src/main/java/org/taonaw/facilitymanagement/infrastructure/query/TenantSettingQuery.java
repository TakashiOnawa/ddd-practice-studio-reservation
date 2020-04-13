package org.taonaw.facilitymanagement.infrastructure.query;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.ITenantSettingRepository;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.PracticeType;
import org.taonaw.facilitymanagement.query.tenantsetting.ITenantSettingQuery;
import org.taonaw.facilitymanagement.query.tenantsetting.TenantSettingDto;

@Repository
@AllArgsConstructor
public class TenantSettingQuery implements ITenantSettingQuery {
    @Autowired
    private final ITenantSettingRepository tenantSettingRepository;

    @Override
    public TenantSettingDto get() {
        var tenantSetting = tenantSettingRepository.get();
        return TenantSettingDto.builder()
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
