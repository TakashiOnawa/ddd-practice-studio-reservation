package org.taonaw.reservation.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Builder
@Getter
public class RegisterReservationRequest {
    private String accountId;
    private int practiceType;
    private String studioId;
    private Date startDateTime;
    private Date endDateTime;
    private int numberOfUsers;
    private Iterable<String> equipmentIds;
}
