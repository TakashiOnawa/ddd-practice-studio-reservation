package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;
import org.taonaw.reservation.domain.model.members.Member;
import org.taonaw.reservation.domain.model.reservations.specificatiions.IReservationValidator;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.*;

public class Reservation {
    private final ReservationId reservationId;
    private StudioId studioId;
    private TimePeriodOfUsage timePeriodOfUsage;
    private UserInformation userInformation;
    private NumberOfUsers numberOfUsers;
    private PracticeTypes practiceType;
    private EquipmentOfUsages equipmentOfUsages;

    private Reservation(@NonNull ReservationId reservationId){
        this.reservationId = reservationId;
    }

    public static Reservation newReservation(
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull UserInformation userInformation,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull PracticeTypes practiceType,
            @NonNull EquipmentOfUsages equipmentOfUsages) {
        var reservation = new Reservation(new ReservationId());
        reservation.studioId = studioId;
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.userInformation = userInformation;
        reservation.numberOfUsers = numberOfUsers;
        reservation.practiceType = practiceType;
        reservation.equipmentOfUsages = equipmentOfUsages;
        return reservation;
    }

    public static Reservation reservedByMember(
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull Member member,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull PracticeTypes practiceType,
            @NonNull EquipmentOfUsages equipmentOfUsages) {
        var reservation = new Reservation(new ReservationId());
        reservation.studioId = studioId;
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.userInformation = UserInformation.of(member);
        reservation.numberOfUsers = numberOfUsers;
        reservation.practiceType = practiceType;
        reservation.equipmentOfUsages = equipmentOfUsages;
        return reservation;
    }

    public static Reservation reconstruct(
            @NonNull ReservationId reservationId,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull UserInformation userInformation,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull PracticeTypes practiceType,
            @NonNull EquipmentOfUsages equipmentOfUsages) {
        var reservation = new Reservation(reservationId);
        reservation.studioId = studioId;
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.userInformation = userInformation;
        reservation.numberOfUsers = numberOfUsers;
        reservation.practiceType = practiceType;
        reservation.equipmentOfUsages = equipmentOfUsages;
        return reservation;
    }

    public ReservationId reservationId() {
        return this.reservationId;
    }
    UserInformation userInformation() {
        return this.userInformation;
    }
    public StudioId studioId() {
        return this.studioId;
    }
    public NumberOfUsers numberOfUsers() {
        return this.numberOfUsers;
    }
    public PracticeTypes practiceType() {
        return this.practiceType;
    }
    public TimePeriodOfUsage timePeriodOfUsage() {
        return  this.timePeriodOfUsage;
    }
    Collection<EquipmentOfUsage> equipmentOfUsages() {
        return this.equipmentOfUsages.values();
    }

    public void addEquipments(@NonNull List<EquipmentId> equipmentIds) {
        equipmentOfUsages.add(equipmentIds);
    }

    public void addEquipment(@NonNull EquipmentId equipmentId) {
        equipmentOfUsages.add(equipmentId);
    }

    public boolean isDuplicated(@NonNull Reservation other) {
        return studioId.equals(other.studioId) && timePeriodOfUsage.isOverlapping(other.timePeriodOfUsage);
    }

    public void validate(@NonNull IReservationValidator validator) {
        validator.validate(this);
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
