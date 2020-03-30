package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.studio.IStudioRepository;
import org.taonaw.facilitymanagement.domain.model.studio.Studio;
import org.taonaw.facilitymanagement.domain.model.studio.StudioId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudioRepository implements IStudioRepository {
    private final static Map<StudioId, Studio> values = new HashMap<>();

    @Override
    public List<Studio> findAll() {
        return values.values().stream()
                .map(item -> DeepCopy.clone(item, Studio.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Studio> findBy(@NonNull StudioId studioId) {
        var studio = values.get(studioId);
        if (studio == null) {
            return Optional.empty();
        }
        return Optional.of(studio);
    }

    @Override
    public void add(@NonNull Studio studio) {
        values.put(studio.getStudioId(), DeepCopy.clone(studio, Studio.class));
    }
}
