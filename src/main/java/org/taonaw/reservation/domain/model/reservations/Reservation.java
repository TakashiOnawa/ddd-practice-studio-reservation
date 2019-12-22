package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.common.domain.exception.DomainException;
import org.taonaw.common.domain.exception.DomainExceptionCodes;
import org.taonaw.reservation.domain.model.accounts.AccountId;
import org.taonaw.reservation.domain.model.reservations.specificatiions.ReservationSpecifications;
import org.taonaw.reservation.domain.model.studios.StudioId;

public class Reservation {
    private final ReservationSpecifications specifications;

    private final ReservationId reservationId;
    private final AccountId accountId;
    private final PracticeTypes practiceType;
    private final RentalEquipments rentalEquipments;

    private StudioId studioId;
    private TimePeriodOfUsage timePeriodOfUsage;
    private NumberOfUsers numberOfUsers;

    private Reservation(
            @NonNull ReservationId reservationId,
            @NonNull AccountId accountId,
            @NonNull PracticeTypes practiceType,
            @NonNull ReservationSpecifications reservationSpecifications){
        this.reservationId = reservationId;
        this.accountId = accountId;
        this.practiceType = practiceType;
        this.rentalEquipments = new RentalEquipments();

        this.specifications = reservationSpecifications;
    }

    public static Reservation newReservation(
            @NonNull AccountId accountId,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull ReservationSpecifications reservationSpecifications) {
        Reservation reservation = new Reservation(new ReservationId(), accountId, practiceType, reservationSpecifications);
        reservation.studioId = studioId;
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;

        reservation.validate();
        return reservation;
    }

    public static Reservation reconstruct(
            @NonNull ReservationId reservationId,
            @NonNull AccountId accountId,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull TimePeriodOfUsage timePeriodOfUsage,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull ReservationSpecifications reservationSpecifications) {
        Reservation reservation = new Reservation(reservationId, accountId, practiceType, reservationSpecifications);
        reservation.studioId = studioId;
        reservation.timePeriodOfUsage = timePeriodOfUsage;
        reservation.numberOfUsers = numberOfUsers;

        return reservation;
    }

    public StudioId studioId() { return this.studioId; }
    public NumberOfUsers numberOfUsers() { return this.numberOfUsers; }
    public PracticeTypes practiceType() { return this.practiceType; }
    public TimePeriodOfUsage timePeriodOfUsage() {
        return  this.timePeriodOfUsage;
    }

    public void addRentalEquipment(@NonNull RentalEquipment rentalEquipment) {
        this.rentalEquipments.add(rentalEquipment);
    }

    public boolean isDuplicated(@NonNull Reservation other) {
        return studioId.equals(other.studioId) && timePeriodOfUsage.isOverlapping(other.timePeriodOfUsage);
    }

    private void validate() {
        if (!specifications.getTimePeriodUnitOfUsageSpecification().isSatisfied(this)) {

        }
        if (!specifications.getReceptionStartTimeSpecification().isSatisfied(this)) {
            throw new DomainException(DomainExceptionCodes.ReservationNotStartedReception);
        }
        if (!specifications.getMaxNumberOfUsersSpecification().isSatisfied(this)) {
            throw new DomainException(DomainExceptionCodes.ReservationOverMaxNumberOfUsers);
        }
    }
}
