package org.taonaw.reservation.application.cancel_reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class CancelReservationByMemberCommand {
    @NonNull private String reservationId;
    @NonNull private String memberId;
}
