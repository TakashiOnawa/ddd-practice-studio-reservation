package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.NonNull;

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
}
