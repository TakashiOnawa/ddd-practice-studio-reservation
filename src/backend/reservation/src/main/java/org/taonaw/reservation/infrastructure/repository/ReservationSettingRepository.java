package org.taonaw.reservation.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;
import org.taonaw.reservation.domain.model.reservation.PracticeType;
import org.taonaw.reservation.domain.model.reservationsetting.*;
import org.taonaw.reservation.domain.model.studio.StudioId;

import java.time.LocalTime;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class ReservationSettingRepository implements IReservationSettingRepository {
    @Autowired
    @Qualifier("facilityManagementRestOptions")
    private final RestOperations facilityManagementRestOptions;

    @Override
    public ReservationSetting findBy(@NonNull StudioId studioId, @NonNull PracticeType practiceType) {
        var uri = "/reservation_setting?studio_id={studioId}&practice_type={practice_type}";
        var response = facilityManagementRestOptions
                .getForEntity(uri, ReservationSettingDto.class, studioId.getValue(), practiceType.getValue());
        var dto = response.getBody();
        Objects.requireNonNull(dto);
        return new ReservationSetting(
                new StudioId(dto.studioId),
                PracticeType.from(dto.practiceType),
                StartTimeType.from(dto.startTimeType),
                dto.isNumberOfUsersUnLimited ? MaxNumberOfUsers.UNLIMITED : new MaxNumberOfUsers(dto.maxNumberOfUsers),
                new ReservationStartDateTime(
                        dto.reservationStartDateValue,
                        ReservationStartDateType.from(dto.reservationStartDateType),
                        dto.reservationStartHour),
                new OpeningHours(dto.openingStartTime, dto.openingEndTime));
    }

    @Getter
    @AllArgsConstructor
    private static class ReservationSettingDto {
        @NonNull private final String studioId;
        private final int practiceType;
        private final int startTimeType;
        private final int maxNumberOfUsers;
        private final boolean isNumberOfUsersUnLimited;
        private final int reservationStartDateValue;
        private final int reservationStartDateType;
        private final int reservationStartHour;
        @NonNull private final LocalTime openingStartTime;
        @NonNull private final LocalTime openingEndTime;
    }
}
