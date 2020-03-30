package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.PracticeType;
import org.taonaw.reservation.domain.model.studio.StudioId;

public interface IReservationSettingRepository {
    ReservationSetting findBy(@NonNull StudioId studioId, @NonNull PracticeType practiceType);
}
