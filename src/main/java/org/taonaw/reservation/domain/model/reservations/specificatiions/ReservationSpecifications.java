package org.taonaw.reservation.domain.model.reservations.specificatiions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ReservationSpecifications {
    @NonNull
    private final TimePeriodUnitOfUsageSpecification timePeriodUnitOfUsageSpecification;
    @NonNull
    private final ReceptionStartTimeSpecification receptionStartTimeSpecification;
    @NonNull
    private final MaxNumberOfUsersSpecification maxNumberOfUsersSpecification;
}
