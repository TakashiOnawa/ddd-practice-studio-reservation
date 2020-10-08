package org.taonaw.studio_reservation.domain.model.practiceTypeSetting;

import java.util.Optional;

public interface PracticeTypeSettingRepository {
    Optional<PracticeTypeSetting> findBy(PracticeTypes practiceType);
}
