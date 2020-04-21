package org.taonaw.facilitymanagement.application.query.studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class StudioDto {
    @NonNull private String studioId;
    @NonNull private String name;
    private double roomSize;
    private int startTimeType;
}
