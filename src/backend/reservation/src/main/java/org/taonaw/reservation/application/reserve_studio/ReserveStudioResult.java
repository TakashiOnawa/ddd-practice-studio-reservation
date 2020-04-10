package org.taonaw.reservation.application.reserve_studio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class ReserveStudioResult {
    @NonNull private String reservationId;
}
