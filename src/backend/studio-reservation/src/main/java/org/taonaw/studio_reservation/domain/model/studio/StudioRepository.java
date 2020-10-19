package org.taonaw.studio_reservation.domain.model.studio;

import java.util.Optional;

public interface StudioRepository {
    Optional<Studio> findBy(StudioId studioId);
    void add(Studio studio);
}
