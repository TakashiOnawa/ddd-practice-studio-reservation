package org.taonaw.reservation.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.application.command.reserve_studio.ReserveStudioByMemberCommand;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioByMemberRequest {
    @NonNull private String studioId;
    @NonNull private LocalDateTime startDateTime;
    @NonNull private LocalDateTime endDateTime;
    private int numberOfUsers;
    private int practiceType;
    @NonNull private List<String> equipmentIds;

    ReserveStudioByMemberCommand getCommand(@NonNull String memberId) {
        return ReserveStudioByMemberCommand.builder()
                .studioId(studioId)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .memberId(memberId)
                .numberOfUsers(numberOfUsers)
                .practiceType(practiceType)
                .equipmentIds(equipmentIds)
                .build();
    }
}
