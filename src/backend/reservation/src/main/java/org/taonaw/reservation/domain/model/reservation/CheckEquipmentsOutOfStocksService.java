package org.taonaw.reservation.domain.model.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.exception.EquipmentOutOfStocksException;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;
import org.taonaw.reservation.domain.model.equipment.IEquipmentRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CheckEquipmentsOutOfStocksService {
    @NonNull
    private final IReservationRepository reservationRepository;
    @NonNull
    private final IEquipmentRepository equipmentRepository;

    public void validate(@NonNull Reservation reservation) {
        var outOfStocksEquipmentIds = getOutOfStocksEquipmentIds(reservation);
        if (outOfStocksEquipmentIds.size() > 0) {
            throw new EquipmentOutOfStocksException(outOfStocksEquipmentIds);
        }
    }

    private List<EquipmentId> getOutOfStocksEquipmentIds(Reservation reservation) {
        var outOfStocksEquipmentIds = new ArrayList<EquipmentId>();

        var overlappedReservedEquipments = Reservations
                .createOverlappedReservations(reservationRepository, reservation)
                .getReservedEquipments();

        for (var useEquipment : reservation.getUseEquipments()) {
            var equipment = equipmentRepository.findBy(useEquipment.getEquipmentId()).orElseThrow();
            var reservedEquipmentQuantity = overlappedReservedEquipments.getQuantity(equipment.getEquipmentId());
            if (equipment.isOutOfStocks(reservedEquipmentQuantity)) {
                outOfStocksEquipmentIds.add(equipment.getEquipmentId());
            }
        }

        return outOfStocksEquipmentIds;
    }
}
