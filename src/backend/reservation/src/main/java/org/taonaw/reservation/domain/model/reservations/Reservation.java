package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;
import org.taonaw.reservation.domain.model.members.Member;
import org.taonaw.reservation.domain.model.reservations.specificatiions.IReservationValidator;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.*;

public class Reservation {
    private final ReservationId reservationId;
    private final UserInformation userInformation;
    private final StudioId studioId;
    private final PracticeTypes practiceType;
    private final Map<EquipmentId, EquipmentOfUsage> equipmentsOfUsage;

    private TimePeriodOfUsage timePeriodOfUsage;
    private NumberOfUsers numberOfUsers;

    private Reservation(
            @NonNull ReservationId reservationId,
            @NonNull UserInformation userInformation,
            @NonNull StudioId studioId,
            @NonNull PracticeTypes practiceType){
        this.reservationId = reservationId;
        this.userInformation = userInformation;
        this.studioId = studioId;
        this.practiceType = practiceType;
        this.equipmentsOfUsage = new HashMap<>();
    }

    public static Reservation newReservation(
            @NonNull UserInformation userInformation,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers) {
        var reservation = new Reservation(new ReservationId(), userInformation, studioId, practiceType);
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;
        return reservation;
    }

    public static Reservation reservedByMember(
            @NonNull Member member,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers) {
        var reservation = new Reservation(new ReservationId(), UserInformation.of(member), studioId, practiceType);
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;
        return reservation;
    }

    public static Reservation reconstruct(
            @NonNull ReservationId reservationId,
            @NonNull UserInformation userInformation,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers) {
        var reservation = new Reservation(reservationId, userInformation, studioId, practiceType);
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;
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
        return Collections.unmodifiableCollection(this.equipmentsOfUsage.values());
    }

    public void addEquipments(@NonNull Collection<EquipmentId> equipmentIds) {
        equipmentIds.forEach(this::addEquipment);
    }

    public void addEquipment(@NonNull EquipmentId equipmentId) {
        if (this.equipmentsOfUsage.containsKey(equipmentId)) {
            var equipmentOfUsage = this.equipmentsOfUsage.get(equipmentId);
            equipmentOfUsage = equipmentOfUsage.addQuantity(1);
            this.equipmentsOfUsage.replace(equipmentId, equipmentOfUsage);
        } else {
            var equipmentOfUsage = new EquipmentOfUsage(equipmentId, 1);
            this.equipmentsOfUsage.put(equipmentId, equipmentOfUsage);
        }
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
