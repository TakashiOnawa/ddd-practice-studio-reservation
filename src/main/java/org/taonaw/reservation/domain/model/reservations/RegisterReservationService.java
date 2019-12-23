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

    private static final Map<String, String> map = Collections.unmodifiableMap(new HashMap<String, String>());


    public void register(@NonNull Reservation reservation) {
        if (isDuplicatedReservation(reservation)) {
            throw new DomainException(DomainExceptionCodes.ReservationDuplication);
        }

        // TODO:機材の在庫チェック
        List<Equipment> equipments = equipmentRepository.findAll();

        TotalRentalEquipments totalRentalEquipments = new TotalRentalEquipments();
        for (Reservation otherReservation : reservationRepository.findTimePeriodOverlapped(reservation)) {
            totalRentalEquipments.add(otherReservation.rentalEquipments());
        }

        reservationRepository.save(reservation);
    }

    private boolean isDuplicatedReservation(Reservation reservation) {
        var sameStudioReservations = reservationRepository.findByStudio(reservation.studioId());
        return sameStudioReservations.stream().anyMatch(other -> reservation.isDuplicated(other));
    }

    private static class TotalRentalEquipments {
        private final HashMap<EquipmentId, Integer> rentalEquipmentsQuantity = new HashMap<>();

        public void add(Collection<RentalEquipment> rentalEquipments) {
            rentalEquipments.forEach(rentalEquipment -> add(rentalEquipment));
        }

        public void add(RentalEquipment rentalEquipment) {
            Integer quantity = rentalEquipmentsQuantity.get(rentalEquipment.getEquipmentId());
            if (quantity == null) {
                quantity = 0;
                rentalEquipmentsQuantity.put(rentalEquipment.getEquipmentId(), quantity);
            }
            rentalEquipmentsQuantity.replace(rentalEquipment.getEquipmentId(), quantity + rentalEquipment.getQuantity());
        }

        public boolean stillHaveStock(List<Equipment> equipments) {
            Map<EquipmentId, EquipmentStockQuantity> equipmentQuantityMap = equipments.stream()
                    .collect(Collectors.toMap(s -> s.equipmentId(), s -> s.stockQuantity()));

            for (EquipmentId rentalEquipmentId : rentalEquipmentsQuantity.keySet()) {
                Integer rentalQuantity = rentalEquipmentsQuantity.get(rentalEquipmentId);
            }

            return true;
        }
    }
}
