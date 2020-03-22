package org.taonaw.reservation.domain.model.studio;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.util.Objects;

public class Studio {
    private final StudioId studioId;
    private final StartTimeType startTimeType;

    public Studio(@NonNull StudioId studioId,
                  @NonNull StartTimeType startTimeType) {
        this.studioId = studioId;
        this.startTimeType = startTimeType;
    }

    public boolean isStartTimeSatisfiedBy(@NonNull UseTime useTime) {
        return startTimeType.isSatisfiedBy(useTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studio studio = (Studio) o;
        return studioId.equals(studio.studioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studioId);
    }
}
