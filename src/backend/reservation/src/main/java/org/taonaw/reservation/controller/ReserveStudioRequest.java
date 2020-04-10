package org.taonaw.reservation.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioByMemberCommand;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioCommand;

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

    boolean isReserveByMember() {
        return memberId != null && !memberId.isEmpty();
    }

    ReserveStudioCommand getReserveStudioCommand() {
        return ReserveStudioCommand.builder()
                .studioId(studioId)
                .startDateTime(startDateTime)
                .hourQuantity(hourQuantity)
                .userName(userName)
                .userPhoneNumber(userPhoneNumber)
                .numberOfUsers(numberOfUsers)
                .practiceType(practiceType)
                .equipmentIds(equipmentIds)
                .build();
    }

    ReserveStudioByMemberCommand getReserveStudioByMemberCommand() {
        return ReserveStudioByMemberCommand.builder()
                .studioId(studioId)
                .startDateTime(startDateTime)
                .hourQuantity(hourQuantity)
                .memberId(memberId)
                .numberOfUsers(numberOfUsers)
                .practiceType(practiceType)
                .equipmentIds(equipmentIds)
                .build();
    }
}
