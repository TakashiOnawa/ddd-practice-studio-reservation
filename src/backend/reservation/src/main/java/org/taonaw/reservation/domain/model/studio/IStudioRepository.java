package org.taonaw.reservation.domain.model.studio;

import lombok.NonNull;

import java.util.Optional;

public interface IStudioRepository {
    Optional<Studio> findBy(@NonNull StudioId studioId);
}
