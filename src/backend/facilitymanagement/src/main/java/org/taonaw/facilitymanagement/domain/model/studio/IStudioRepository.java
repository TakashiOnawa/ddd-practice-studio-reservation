package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.NonNull;

public interface IStudioRepository {
    void add(@NonNull Studio studio);
}
