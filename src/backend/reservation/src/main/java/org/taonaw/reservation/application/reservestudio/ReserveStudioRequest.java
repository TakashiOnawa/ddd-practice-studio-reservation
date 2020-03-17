package org.taonaw.reservation.application.reservestudio;

import lombok.*;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<EquipmentId> getEquipmentIds() {
        return this.equipmentIds.stream().map(EquipmentId::new).collect(Collectors.toList());
    }
}
