package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;

import java.util.Optional;

public interface ReservationRuleFactory {
    Optional<ReservationRule> create(@NonNull StudioId studioId, @NonNull PracticeTypes practiceType);
}
