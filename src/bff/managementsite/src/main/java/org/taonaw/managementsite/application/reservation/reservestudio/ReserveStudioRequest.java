package org.taonaw.managementsite.application.reservation.reservestudio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioRequest {

    @NonNull
    private String studioId;

    @NonNull
    private Date startDateTime;

    @NonNull
    private Date endDateTime;

    @NonNull
    private String userName;

    @NonNull
    private String userPhoneNumber;

    private int numberOfUsers;

    private int practiceType;

    @NonNull
    private List<String> equipmentIds;
}
