package org.taonaw.managementsite.application.reservation.command.reserve_studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioRequest {
    private String studioId;
    private LocalDateTime startDateTime;
    private int hourQuantity;
    private String memberId;
    private String userName;
    private String userPhoneNumber;
    private int numberOfUsers;
    private int practiceType;
    private List<String> equipmentIds;
}
