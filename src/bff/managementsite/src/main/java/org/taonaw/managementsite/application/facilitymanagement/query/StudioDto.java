package org.taonaw.managementsite.application.facilitymanagement.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudioDto {
    private String studioId;
    private String name;
    private double roomSize;
    private int startTimeType;
}
