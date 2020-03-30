package org.taonaw.facilitymanagement.query.studio;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IStudioQuery {
    List<StudioDto> getAll();
    Optional<StudioDto> getByStudioId(@NonNull String id);
}
