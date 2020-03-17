package org.taonaw.reservation.application.reservestudio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioResponse {

    @NonNull
    private String reservationId;
}
