package org.taonaw.reservation.application.change_reservation;

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
public class ChangeReservationCommand {
    @NonNull private String reservationId;
    @NonNull private LocalDateTime startDateTime;
    @NonNull private LocalDateTime endDateTime;
    @NonNull private String userName;
    @NonNull private String userPhoneNumber;
    private int numberOfUsers;
    private int practiceType;
    @NonNull private List<String> equipmentIds;

    public List<EquipmentId> getEquipmentIds() {
        return equipmentIds.stream().map(EquipmentId::new).collect(Collectors.toList());
    }
}
