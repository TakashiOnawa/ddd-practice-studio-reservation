package org.taonaw.managementsite.application.facilitymanagement.command.register_studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterStudioRequest {
    @NonNull
    private String name;
    private double roomSize;
    private int startTimeType;
}
