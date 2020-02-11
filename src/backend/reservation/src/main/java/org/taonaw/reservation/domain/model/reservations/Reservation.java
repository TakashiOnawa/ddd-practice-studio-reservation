package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;
import org.taonaw.reservation.domain.model.members.Member;
import org.taonaw.reservation.domain.model.members.MemberId;
import org.taonaw.reservation.domain.model.reservations.specificatiions.IReservationValidator;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.*;

public class Reservation {
    private final ReservationId reservationId;
    private final MemberId memberId;
    private final StudioId studioId;
    private final PracticeTypes practiceType;
    private final Map<EquipmentId, EquipmentOfUsage> equipmentOfUsages;

    private TimePeriodOfUsage timePeriodOfUsage;
    private NumberOfUsers numberOfUsers;
    private UserInformation userInformation;

    private Reservation(
            @NonNull ReservationId reservationId,
            @NonNull MemberId memberId,
            @NonNull StudioId studioId,
            @NonNull PracticeTypes practiceType){
        this.reservationId = reservationId;
        this.memberId = memberId;
        this.studioId = studioId;
        this.practiceType = practiceType;
        this.equipmentOfUsages = new HashMap<>();
    }

    public static Reservation newReservation(
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull UserInformation userInformation) {
        var reservation = new Reservation(new ReservationId(), MemberId.nonMember(), studioId, practiceType);
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;
        reservation.userInformation = userInformation;
        return reservation;
    }

    public static Reservation reservedByMember(
            @NonNull Member member,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers) {
        var reservation = new Reservation(new ReservationId(), member.memberId(), studioId, practiceType);
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;
        reservation.userInformation = UserInformation.of(member);
        return reservation;
    }

    public static Reservation reconstruct(
            @NonNull ReservationId reservationId,
            @NonNull MemberId memberId,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull UserInformation userInformation) {
        var reservation = new Reservation(reservationId, memberId, studioId, practiceType);
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;
        reservation.userInformation = userInformation;
        return reservation;
    }

    public ReservationId reservationId() {
        return this.reservationId;
    }
    MemberId memberId() {
        return this.memberId;
    }
    StudioId studioId() {
        return this.studioId;
    }
    NumberOfUsers numberOfUsers() {
        return this.numberOfUsers;
    }
    PracticeTypes practiceType() {
        return this.practiceType;
    }
    TimePeriodOfUsage timePeriodOfUsage() {
        return  this.timePeriodOfUsage;
    }
    Collection<EquipmentOfUsage> equipmentOfUsages() {
        return Collections.unmodifiableCollection(this.equipmentOfUsages.values());
    }

    public void addEquipments(@NonNull Collection<EquipmentId> equipmentIds) {
        equipmentIds.forEach(this::addEquipment);
    }

    public void addEquipment(@NonNull EquipmentId equipmentId) {
        if (this.equipmentOfUsages.containsKey(equipmentId)) {
            var equipmentOfUsage = this.equipmentOfUsages.get(equipmentId);
            equipmentOfUsage = equipmentOfUsage.addQuantity(1);
            this.equipmentOfUsages.replace(equipmentId, equipmentOfUsage);
        } else {
            var equipmentOfUsage = new EquipmentOfUsage(equipmentId, 1);
            this.equipmentOfUsages.put(equipmentId, equipmentOfUsage);
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
