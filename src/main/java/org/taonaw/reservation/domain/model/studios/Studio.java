package org.taonaw.reservation.domain.model.studios;

import lombok.NonNull;

public class Studio {
    private final StudioId studioId;

    private StudioName studioName;

    private Studio(@NonNull StudioId studioId) {
        this.studioId = studioId;
    }

    public static Studio newStudio(@NonNull StudioName studioName) {
        Studio studio = new Studio(new StudioId());
        studio.studioName = studioName;
        return studio;
    }

    public static Studio reconstruct(@NonNull StudioId studioId,
                                     @NonNull StudioName studioName) {
        Studio studio = new Studio(studioId);
        studio.studioName = studioName;
        return studio;
    }
}
