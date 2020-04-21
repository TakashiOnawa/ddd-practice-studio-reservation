package org.taonaw.facilitymanagement.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.domain.model.studio.IStudioRepository;
import org.taonaw.facilitymanagement.domain.model.studio.Studio;
import org.taonaw.facilitymanagement.domain.model.studio.StudioId;
import org.taonaw.facilitymanagement.application.query.studio.IStudioQuery;
import org.taonaw.facilitymanagement.application.query.studio.StudioDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class StudioQuery implements IStudioQuery {
    @Autowired
    private final IStudioRepository studioRepository;

    @Override
    public List<StudioDto> getAll() {
        return studioRepository.findAll().stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudioDto> getByStudioId(@NonNull String studioId) {
        var res = studioRepository.findBy(new StudioId(studioId));
        if (res.isEmpty()) {
            return Optional.empty();
        }
        var studio = res.get();
        return Optional.of(create(studio));
    }

    private StudioDto create(Studio studio) {
        return StudioDto.builder()
                .studioId(studio.getStudioId().getValue())
                .name(studio.getName().getValue())
                .roomSize(studio.getRoomSize().getValue())
                .startTimeType(studio.getStartTimeType().getStartMinutes())
                .build();
    }
}
