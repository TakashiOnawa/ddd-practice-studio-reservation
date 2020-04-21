package org.taonaw.reservation.application.command.cancel_reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class CancelReservationCommand {
    @NonNull private String reservationId;
}
