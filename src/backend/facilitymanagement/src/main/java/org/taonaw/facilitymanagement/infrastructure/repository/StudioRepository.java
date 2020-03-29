package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.studio.IStudioRepository;
import org.taonaw.facilitymanagement.domain.model.studio.Studio;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudioRepository implements IStudioRepository {
    private final static List<Studio> values = new ArrayList<>();

    @Override
    public void add(@NonNull Studio studio) {
        values.add(DeepCopy.clone(studio, Studio.class));
    }
}
