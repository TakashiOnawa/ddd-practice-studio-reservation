package org.taonaw.facilitymanagement.infrastructure.query;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.ITenantSettingRepository;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.PracticeType;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.TenantSetting;
import org.taonaw.facilitymanagement.application.query.tenantsetting.CancellationFeeRateDto;
import org.taonaw.facilitymanagement.application.query.tenantsetting.ITenantSettingQuery;
import org.taonaw.facilitymanagement.application.query.tenantsetting.TenantSettingDto;

import java.util.List;
import java.util.stream.Collectors;

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
                .cancellationFeeRates(getCancellationFeeRates(tenantSetting))
                .build();
    }

    private List<CancellationFeeRateDto> getCancellationFeeRates(TenantSetting tenantSetting) {
        return tenantSetting.getCancellationFeeRates().stream()
                .map(item -> CancellationFeeRateDto.builder()
                        .daysAgo(item.getDaysAgo())
                        .rate(item.getRate())
                        .build())
                .collect(Collectors.toList());
    }
}
