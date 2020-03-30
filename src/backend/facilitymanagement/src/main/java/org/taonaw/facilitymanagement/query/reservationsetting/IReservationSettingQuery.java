package org.taonaw.facilitymanagement.query.reservationsetting;

import lombok.NonNull;

public interface IReservationSettingQuery {
    ReservationSettingDto getByStudioAndPracticeType(@NonNull String studioId, int practiceType);
}
