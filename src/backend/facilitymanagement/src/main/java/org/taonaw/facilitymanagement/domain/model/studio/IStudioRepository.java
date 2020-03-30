package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IStudioRepository {
    List<Studio> findAll();
    void add(@NonNull Studio studio);
    Optional<Studio> findBy(StudioId studioId);
}
