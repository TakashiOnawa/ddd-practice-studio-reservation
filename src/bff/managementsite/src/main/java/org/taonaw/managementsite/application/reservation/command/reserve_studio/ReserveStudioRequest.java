package org.taonaw.managementsite.application.reservation.command.reserve_studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioRequest {
    @NonNull private String studioId;
    @NonNull private LocalDateTime startDateTime;
    private int hourQuantity;
    @NonNull private String userName;
    @NonNull private String userPhoneNumber;
    private int numberOfUsers;
    private int practiceType;
    @NonNull private List<String> equipmentIds;
}
