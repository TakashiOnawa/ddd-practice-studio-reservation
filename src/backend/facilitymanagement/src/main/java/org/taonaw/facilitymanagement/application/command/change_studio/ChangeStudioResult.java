package org.taonaw.facilitymanagement.application.command.change_studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.model.studio.Studio;

@Getter
@Builder
@AllArgsConstructor
public class ChangeStudioResult {
    @NonNull private String studioId;
    @NonNull private String name;
    private double roomSize;
    private int startTimeType;

    static ChangeStudioResult of(Studio studio) {
        return builder()
                .studioId(studio.getStudioId().getValue())
                .name(studio.getName().getValue())
                .roomSize(studio.getRoomSize().getValue())
                .startTimeType(studio.getStartTimeType().getValue())
                .build();
    }
}
