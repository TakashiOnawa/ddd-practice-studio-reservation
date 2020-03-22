package org.taonaw.reservation.domain.model.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
class Reservations {
    private final Collection<Reservation> reservations;

    public static Reservations createOverlappedOtherReservations(@NonNull IReservationRepository reservationRepository,
                                                                 @NonNull Reservation reservation) {
        var useTime = reservation.getUseTime();
        var reservations = reservationRepository.findByDateRange(useTime.getStartDate(), useTime.getEndDate());
        return new Reservations((reservations.stream()
                .filter(item -> !item.equals(reservation) && item.isOverlapped(reservation)))
                .collect(Collectors.toList()));
    }

    public static Reservations createOverlappedReservations(@NonNull IReservationRepository reservationRepository,
                                                            @NonNull Reservation reservation) {
        var overlappedReservations = createOverlappedOtherReservations(reservationRepository, reservation);
        overlappedReservations.reservations.add(reservation);
        return overlappedReservations;
    }

    public boolean exists() {
        return !reservations.isEmpty();
    }

    public ReservedEquipments getReservedEquipments() {
        return ReservedEquipments.from(reservations);
    }
}
