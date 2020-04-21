package org.taonaw.reservation.application.command.change_reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ChangeReservationResult {
    @NonNull private String reservationId;
    @NonNull private LocalDateTime startDateTime;
    @NonNull private LocalDateTime endDateTime;
    @NonNull private String memberId;
    @NonNull private String userName;
    @NonNull private String userPhoneNumber;
    private int numberOfUsers;
    private int practiceType;
    @NonNull private List<String> equipmentIds;

    static ChangeReservationResult of(Reservation reservation) {
        return builder()
                .reservationId(reservation.getReservationId().getValue())
                .startDateTime(reservation.getUseTime().getStart())
                .endDateTime(reservation.getUseTime().getEnd())
                .memberId(reservation.getUserInformation().getMemberId().getValue())
                .userName(reservation.getUserInformation().getName())
                .userPhoneNumber(reservation.getUserInformation().getPhoneNumber())
                .numberOfUsers(reservation.getNumberOfUsers().getValue())
                .practiceType(reservation.getPracticeType().getValue())
                .equipmentIds(getEquipmentIds(reservation))
                .build();
    }

    private static List<String> getEquipmentIds(Reservation reservation) {
        var equipmentIds = new ArrayList<String>();
        for (var useEquipment : reservation.getUseEquipments()) {
            for (int i = 0; i < useEquipment.getQuantity(); i++) {
                equipmentIds.add(useEquipment.getEquipmentId().getValue());
            }
        }
        return equipmentIds;
    }
}
