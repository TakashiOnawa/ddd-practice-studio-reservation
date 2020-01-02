package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.members.MemberId;
import org.taonaw.reservation.domain.model.practicetypes.PracticeTypes;
import org.taonaw.reservation.domain.model.rentalequipments.RentalEquipmentId;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Reservation {
    private final ReservationId reservationId;
    private final MemberId memberId;
    private final PracticeTypes practiceType;
    private final Map<RentalEquipmentId, EquipmentOfUsage> equipmentOfUsages;

    private StudioId studioId;
    private TimePeriodOfUsage timePeriodOfUsage;
    private NumberOfUsers numberOfUsers;

    private Reservation(
            @NonNull ReservationId reservationId,
            @NonNull MemberId memberId,
            @NonNull PracticeTypes practiceType){
        this.reservationId = reservationId;
        this.memberId = memberId;
        this.practiceType = practiceType;
        this.equipmentOfUsages = new HashMap<>();
    }

    public static Reservation newReservation(
            @NonNull MemberId memberId,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers) {
        Reservation reservation = new Reservation(new ReservationId(), memberId, practiceType);
        reservation.studioId = studioId;
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;
        return reservation;
    }

    public static Reservation reconstruct(
            @NonNull ReservationId reservationId,
            @NonNull MemberId memberId,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers) {
        Reservation reservation = new Reservation(reservationId, memberId, practiceType);
        reservation.studioId = studioId;
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;

        return reservation;
    }

    public StudioId studioId() { return this.studioId; }
    public NumberOfUsers numberOfUsers() { return this.numberOfUsers; }
    public PracticeTypes practiceType() { return this.practiceType; }
    public TimePeriodOfUsage timePeriodOfUsage() { return  this.timePeriodOfUsage; }
    public Collection<EquipmentOfUsage> equipmentOfUsages() { return this.equipmentOfUsages.values(); }

    public void increaseRentalEquipment(@NonNull RentalEquipmentId rentalEquipmentId) {
        if (this.equipmentOfUsages.containsKey(rentalEquipmentId)) {
            EquipmentOfUsage targetRentalEquipment = this.equipmentOfUsages.get(rentalEquipmentId);
            targetRentalEquipment = targetRentalEquipment.increase(1);
            this.equipmentOfUsages.replace(targetRentalEquipment.getRentalEquipmentId(), targetRentalEquipment);
        } else {
            EquipmentOfUsage targetRentalEquipment = new EquipmentOfUsage(rentalEquipmentId, 1);
            this.equipmentOfUsages.put(rentalEquipmentId, targetRentalEquipment);
        }
    }

    public boolean isDuplicated(@NonNull Reservation other) {
        return studioId.equals(other.studioId) && timePeriodOfUsage.isOverlapping(other.timePeriodOfUsage);
    }

    public boolean isTimePeriodOverlaped(@NonNull Reservation other) {
        return timePeriodOfUsage.isOverlapping(other.timePeriodOfUsage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId.equals(that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }
}
