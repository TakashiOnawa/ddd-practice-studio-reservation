package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.studio.IStudioRepository;
import org.taonaw.facilitymanagement.domain.model.studio.Studio;
import org.taonaw.facilitymanagement.domain.model.studio.StudioId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudioRepository implements IStudioRepository {
    private final static Map<StudioId, Studio> values = new HashMap<>();

    @Override
    public void add(@NonNull Studio studio) {
        values.put(studio.getStudioId(), DeepCopy.clone(studio, Studio.class));
    }

    @Override
    public Optional<Studio> findBy(@NonNull StudioId studioId) {
        var studio = values.get(studioId);
        if (studio == null) {
            return Optional.empty();
        }
        return Optional.of(studio);
    }
}
