package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.reservation.error.ReservationDuplicatedError;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Reservations {
    private final List<Reservation> items;

    public Reservations(@NonNull List<Reservation> items) {
        this.items = new ArrayList<>(items);
    }

    public Optional<Error> validateDuplicated(@NonNull Reservation reservation) {
        if (items.stream().anyMatch(item -> item.isDuplicated(reservation))) {
            return Optional.of(new ReservationDuplicatedError());
        }
        return Optional.empty();
    }

    public Optional<Error> validateUsageEquipmentsOutOfStocks(
            @NonNull Reservation reservation,
            @NonNull ReservationRule reservationRule) {

        return reservationRule.validateUsageEquipmentsOutOfStocks(ReservedUsageEquipments.create(items, reservation));
    }
}
