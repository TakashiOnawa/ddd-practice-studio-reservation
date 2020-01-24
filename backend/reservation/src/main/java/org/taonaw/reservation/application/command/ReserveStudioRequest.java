package org.taonaw.reservation.application.command;

import lombok.*;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
public class ReserveStudioRequest {
    @NonNull
    private String memberId;
    private int practiceType;
    @NonNull
    private String studioId;
    @NonNull
    private Date startDateTime;
    @NonNull
    private Date endDateTime;
    private int numberOfUsers;
    @NonNull
    private Collection<String> equipments;

    public List<EquipmentId> getEquipmentIds() {
        return this.equipments.stream().map(item -> new EquipmentId(item)).collect(Collectors.toList());
    }
}
