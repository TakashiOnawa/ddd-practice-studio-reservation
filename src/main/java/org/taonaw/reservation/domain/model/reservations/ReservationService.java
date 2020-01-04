package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.taonaw.reservation.domain.model.equipments.IEquipmentRepository;

@Component
@AllArgsConstructor
public class ReservationService {

    @Autowired
    private final IReservationRepository reservationRepository;
    @Autowired
    private final IEquipmentRepository equipmentRepository;

    public boolean equipmentOutOfStock(@NonNull Reservation reservation) {
        var reservedEquipments = getSameTimeReservedEquipments(reservation);
        var allEquipments = equipmentRepository.findAll();
        return reservedEquipments.isOutOfStock(allEquipments);
    }

    public boolean alreadyReserved(@NonNull Reservation reservation) {
        var sameStudioReservations = reservationRepository.findByStudio(reservation.studioId());
        return sameStudioReservations.stream()
                .anyMatch(other -> reservation.isDuplicated(other) && !reservation.equals(other));
    }

    public ReservedEquipments getSameTimeReservedEquipments(@NonNull Reservation reservation) {
        var sameTimeReservations = reservationRepository.findTimePeriodOverlapped(reservation);
        sameTimeReservations.removeIf(s -> s.equals(reservation));
        sameTimeReservations.add(reservation);
        return ReservedEquipments.Create(sameTimeReservations);
    }
}
