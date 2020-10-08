package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.Equipment;
import org.taonaw.studio_reservation.domain.model.reservation.error.ReservationDuplicatedError;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.List;
import java.util.Optional;

public class ReservationValidator {
    public Optional<Error> validateDuplication(
            @NonNull Reservation reservation,
            @NonNull List<Reservation> reservations) {

        if (reservations.stream().anyMatch(item -> item.isDuplicated(reservation))) {
            return Optional.of(new ReservationDuplicatedError());
        }
        return Optional.empty();
    }

    public Optional<Error> validateEquipmentOutOfStocks(
            @NonNull Reservation reservation,
            @NonNull List<Reservation> reservations,
            @NonNull List<Equipment> equipments) {

        return Optional.empty();
    }
}
