package org.taonaw.facilitymanagement.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.domain.model.studio.IStudioRepository;
import org.taonaw.facilitymanagement.domain.model.studio.StudioId;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.ITenantSettingRepository;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.PracticeType;
import org.taonaw.facilitymanagement.query.reservationsetting.IReservationSettingQuery;
import org.taonaw.facilitymanagement.query.reservationsetting.ReservationSettingDto;

@Repository
@AllArgsConstructor
public class ReservationSettingQuery implements IReservationSettingQuery {
    @Autowired
    private final IStudioRepository studioRepository;
    @Autowired
    private final ITenantSettingRepository tenantSettingRepository;

    @Override
    public ReservationSettingDto getByStudioIdAndPracticeType(@NonNull String studioId, int practiceType) {
        var studio = studioRepository.findBy(new StudioId(studioId)).orElseThrow();
        var tenantSetting = tenantSettingRepository.get();

        var practiceTypeObj = PracticeType.from(practiceType);
        var reservationStartTime = tenantSetting.getReservationStartDateTime(practiceTypeObj);
        var maxNumberOfUsers = tenantSetting.getMaxNumberOfUsers(practiceTypeObj);
        return ReservationSettingDto.builder()
                .studioId(studioId)
                .practiceType(practiceType)
                .startTimeType(studio.getStartTimeType().getValue())
                .maxNumberOfUsers(maxNumberOfUsers.getValue())
                .isNumberOfUsersUnLimited(maxNumberOfUsers.isUnLimited())
                .reservationStartDateValue(reservationStartTime.getStartDateValue())
                .reservationStartDateType(reservationStartTime.getStartDateType().getValue())
                .reservationStartHour(reservationStartTime.getStartHour())
                .openingStartTime(tenantSetting.getOpeningHours().getStart())
                .openingEndTime(tenantSetting.getOpeningHours().getEnd())
                .build();
    }
}
