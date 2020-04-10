package org.taonaw.facilitymanagement.query.reservationsetting;

import lombok.NonNull;

public interface IReservationSettingQuery {
    ReservationSettingDto getByStudioIdAndPracticeType(@NonNull String studioId, int practiceType);
}
