package org.taonaw.reservation.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.studio.IStudioRepository;
import org.taonaw.reservation.domain.model.studio.Studio;
import org.taonaw.reservation.domain.model.studio.StudioId;

import java.util.Optional;

@Repository
public class StudioRepository implements IStudioRepository {
    @Override
    public Optional<Studio> findBy(@NonNull StudioId studioId) {
        return Optional.empty();
    }
}
