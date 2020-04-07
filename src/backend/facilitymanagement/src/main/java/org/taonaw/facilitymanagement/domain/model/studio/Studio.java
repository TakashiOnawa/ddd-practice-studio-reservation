package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.NonNull;

import java.util.Objects;

public class Studio {
    private StudioId studioId;
    private StudioName name;
    private StudioRoomSize roomSize;
    private StartTimeType startTimeType;

    private Studio() { }

    public static Studio newStudio(@NonNull StudioName name,
                                   @NonNull StudioRoomSize roomSize,
                                   @NonNull StartTimeType startTimeType) {
        var studio = new Studio();
        studio.studioId = StudioId.newId();
        studio.name = name;
        studio.roomSize = roomSize;
        studio.startTimeType = startTimeType;
        return studio;
    }

    public static Studio reconstruct(@NonNull StudioId studioId,
                                     @NonNull StudioName name,
                                     @NonNull StudioRoomSize roomSize,
                                     @NonNull StartTimeType startTimeType) {
        var studio = new Studio();
        studio.studioId = studioId;
        studio.name = name;
        studio.roomSize = roomSize;
        studio.startTimeType = startTimeType;
        return studio;
    }

    public StudioId getStudioId() {
        return studioId;
    }

    public StudioName getName() {
        return name;
    }

    public StudioRoomSize getRoomSize() {
        return roomSize;
    }

    public StartTimeType getStartTimeType() {
        return startTimeType;
    }

    public void changeName(@NonNull StudioName name) {
        this.name = name;
    }

    public void changeRoomSize(@NonNull StudioRoomSize roomSize) {
        this.roomSize = roomSize;
    }

    public void changeStartTimeType(@NonNull StartTimeType startTimeType) {
        this.startTimeType = startTimeType;
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
