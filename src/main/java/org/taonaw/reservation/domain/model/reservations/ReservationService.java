package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.rentalequipments.IRentalEquipmentRepository;

@AllArgsConstructor
public class ReservationService {
    @NonNull
    private final IReservationRepository reservationRepository;
    @NonNull
    private final IRentalEquipmentRepository rentalEquipmentRepository;

    public boolean equipmentOutOfStock(@NonNull Reservation reservation) {
//        TotalRentalEquipments totalRentalEquipments = new TotalRentalEquipments();
//        for (Reservation otherReservation : reservationRepository.findTimePeriodOverlapped(reservation)) {
//            totalRentalEquipments.add(otherReservation.equipmentOfUsages());
//        }
//        return !totalRentalEquipments.isInStock(rentalEquipmentRepository.findAll());
        return false;
    }

    public boolean alreadyReserved(@NonNull Reservation reservation) {
        var sameStudioReservations = reservationRepository.findByStudio(reservation.studioId());
        return sameStudioReservations.stream()
                .anyMatch(other -> reservation.isDuplicated(other) && !reservation.equals(other));
    }

//    private static class TotalRentalEquipments {
//        private final Map<RentalEquipmentId, RentalEquipmentStock> totalRentalEquipmentsQuantity = new HashMap<>();
//
//        void add(Collection<EquipmentOfUsage> rentalEquipments) {
//            rentalEquipments.forEach(this::add);
//        }
//
//        void add(EquipmentOfUsage rentalEquipment) {
//            RentalEquipmentStock quantity = totalRentalEquipmentsQuantity
//                    .computeIfAbsent(rentalEquipment.getRentalEquipmentId(), s -> RentalEquipmentStock.empty());
//
//            quantity = quantity.add(new RentalEquipmentStock(rentalEquipment.getQuantity()));
//            totalRentalEquipmentsQuantity.replace(rentalEquipment.getRentalEquipmentId(), quantity);
//        }
//
//        boolean isInStock(List<RentalEquipment> equipments) {
//            Map<RentalEquipmentId, RentalEquipmentStock> equipmentQuantityMap = equipments.stream()
//                    .collect(Collectors.toMap(RentalEquipment::rentalEquipmentId, RentalEquipment::stockQuantity));
//
//            for (RentalEquipmentId rentalEquipmentId : totalRentalEquipmentsQuantity.keySet()) {
//                RentalEquipmentStock rentalEquipmentQuantity = totalRentalEquipmentsQuantity.get(rentalEquipmentId);
//
//                RentalEquipmentStock rentalEquipmentStock = equipmentQuantityMap.get(rentalEquipmentId);
//                if (rentalEquipmentStock == null) {
//                    return  false;
//                }
//
//                if (rentalEquipmentQuantity.greaterThan(rentalEquipmentStock)) {
//                    return false;
//                }
//            }
//
//            return true;
//        }
//    }
}
