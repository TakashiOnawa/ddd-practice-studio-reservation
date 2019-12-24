package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.common.domain.exception.DomainException;
import org.taonaw.common.domain.exception.DomainExceptionCodes;
import org.taonaw.reservation.domain.model.equipments.Equipment;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;
import org.taonaw.reservation.domain.model.equipments.EquipmentStockQuantity;
import org.taonaw.reservation.domain.model.equipments.IEquipmentRepository;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RegisterReservationService {
    @NonNull
    private final IReservationRepository reservationRepository;
    @NonNull
    private final IEquipmentRepository equipmentRepository;

    public void register(@NonNull Reservation reservation) {
        if (isDuplicatedReservation(reservation)) {
            throw new DomainException(DomainExceptionCodes.ReservationDuplication);
        }

        if (isNotInEquipmentStock(reservation)) {
            throw new DomainException(DomainExceptionCodes.ReservedEquipmentsNotInStock);
        }

        reservationRepository.save(reservation);
    }

    private boolean isDuplicatedReservation(Reservation reservation) {
        var sameStudioReservations = reservationRepository.findByStudio(reservation.studioId());
        return sameStudioReservations.stream().anyMatch(reservation::isDuplicated);
    }

    private boolean isNotInEquipmentStock(Reservation reservation) {
        TotalRentalEquipments totalRentalEquipments = new TotalRentalEquipments();
        for (Reservation otherReservation : reservationRepository.findTimePeriodOverlapped(reservation)) {
            totalRentalEquipments.add(otherReservation.rentalEquipments());
        }
        return !totalRentalEquipments.isInStock(equipmentRepository.findAll());
    }

    private static class TotalRentalEquipments {
        private final HashMap<EquipmentId, EquipmentStockQuantity> totalRentalEquipmentsQuantity = new HashMap<>();

        void add(Collection<RentalEquipment> rentalEquipments) {
            rentalEquipments.forEach(this::add);
        }

        void add(RentalEquipment rentalEquipment) {
            EquipmentStockQuantity quantity = totalRentalEquipmentsQuantity
                    .computeIfAbsent(rentalEquipment.getEquipmentId(), s -> EquipmentStockQuantity.empty());

            quantity = quantity.add(new EquipmentStockQuantity(rentalEquipment.getQuantity()));
            totalRentalEquipmentsQuantity.replace(rentalEquipment.getEquipmentId(), quantity);
        }

        boolean isInStock(List<Equipment> equipments) {
            Map<EquipmentId, EquipmentStockQuantity> equipmentQuantityMap = equipments.stream()
                    .collect(Collectors.toMap(Equipment::equipmentId, Equipment::stockQuantity));

            for (EquipmentId rentalEquipmentId : totalRentalEquipmentsQuantity.keySet()) {
                EquipmentStockQuantity rentalEquipmentQuantity = totalRentalEquipmentsQuantity.get(rentalEquipmentId);

                EquipmentStockQuantity equipmentStockQuantity = equipmentQuantityMap.get(rentalEquipmentId);
                if (equipmentStockQuantity == null) {
                    return  false;
                }

                if (rentalEquipmentQuantity.greaterThan(equipmentStockQuantity)) {
                    return false;
                }
            }

            return true;
        }
    }
}
