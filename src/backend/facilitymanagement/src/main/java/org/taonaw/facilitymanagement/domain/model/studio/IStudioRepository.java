package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.NonNull;

import java.util.Optional;

public interface IStudioRepository {
    void add(@NonNull Studio studio);
    Optional<Studio> findBy(StudioId studioId);
}
