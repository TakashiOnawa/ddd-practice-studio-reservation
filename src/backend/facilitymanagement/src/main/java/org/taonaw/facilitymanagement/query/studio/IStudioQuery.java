package org.taonaw.facilitymanagement.query.studio;

import lombok.NonNull;

import java.util.Optional;

public interface IStudioQuery {
    Optional<StudioDto> getByStudioId(@NonNull String id);
}
