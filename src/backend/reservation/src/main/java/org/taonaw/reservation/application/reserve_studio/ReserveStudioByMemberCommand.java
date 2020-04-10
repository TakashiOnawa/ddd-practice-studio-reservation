package org.taonaw.reservation.application.reserve_studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioByMemberCommand {
    @NonNull private String studioId;
    @NonNull private LocalDateTime startDateTime;
    private int hourQuantity;
    @NonNull private String memberId;
    private int numberOfUsers;
    private int practiceType;
    @NonNull private List<String> equipmentIds;

    public List<EquipmentId> getEquipmentIds() {
        return equipmentIds.stream().map(EquipmentId::new).collect(Collectors.toList());
    }
}
