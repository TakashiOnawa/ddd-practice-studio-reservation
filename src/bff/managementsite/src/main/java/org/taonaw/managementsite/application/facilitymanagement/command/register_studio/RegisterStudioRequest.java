package org.taonaw.managementsite.application.facilitymanagement.command.register_studio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterStudioRequest {
    private String name;
    private double roomSize;
    private int startTimeType;
}
