package org.taonaw.reservation.domain.model.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipment.IEquipmentRepository;
import org.taonaw.reservation.domain.shared.exception.DomainException;
import org.taonaw.reservation.domain.shared.exception.DomainExceptionCodes;

@AllArgsConstructor
public class CheckEquipmentsOutOfStocksService {
    private final IReservationRepository reservationRepository;
    private final IEquipmentRepository equipmentRepository;

    public boolean isOutOfStocks(@NonNull Reservation reservation) {
        var overlappedReservedEquipments = Reservations
                .createOverlappedReservations(reservationRepository, reservation)
                .getReservedEquipments();

        for (var useEquipment : reservation.getUseEquipments()) {
            var equipment = equipmentRepository.findBy(useEquipment.getEquipmentId()).orElseThrow();
            var reservedEquipmentQuantity = overlappedReservedEquipments.getQuantity(equipment.getEquipmentId());
            if (equipment.isOutOfStocks(reservedEquipmentQuantity)) {
                return false;
            }
        }
        return true;
    }

    public void validate(@NonNull Reservation reservation) {
        if (isOutOfStocks(reservation)) {
            throw new DomainException(DomainExceptionCodes.EquipmentOutOfStocks);
        }
    }
}
