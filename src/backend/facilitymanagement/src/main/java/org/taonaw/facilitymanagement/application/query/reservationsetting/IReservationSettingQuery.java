package org.taonaw.facilitymanagement.application.query.reservationsetting;

import lombok.NonNull;

public interface IReservationSettingQuery {
    ReservationSettingDto getByStudioIdAndPracticeType(@NonNull String studioId, int practiceType);
}
