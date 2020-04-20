package org.taonaw.reservation.application.reserve_studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.Reservation;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioResult {
    @NonNull private String reservationId;

    static ReserveStudioResult of(Reservation reservation) {
        return builder()
                .reservationId(reservation.getReservationId().getValue())
                .build();
    }
}
