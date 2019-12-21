package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.common.domain.exception.DomainException;
import org.taonaw.common.domain.exception.DomainExceptionCodes;
import org.taonaw.reservation.domain.model.accounts.AccountId;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.Calendar;
import java.util.Date;

public class Reservation {

    private final ReservationId reservationId;
    private final AccountId accountId;
    private final PracticeTypes practiceType;
    private final RentalEquipments rentalEquipments;

    private StudioId studioId;
    private ReservationTime reservationTime;
    private NumberOfUsers numberOfUsers;

    private Reservation(
            @NonNull ReservationId reservationId,
            @NonNull AccountId accountId,
            @NonNull PracticeTypes practiceType){
        this.reservationId = reservationId;
        this.accountId = accountId;
        this.practiceType = practiceType;
        this.rentalEquipments = new RentalEquipments();
    }

    public static Reservation newReservation(
            @NonNull AccountId accountId,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull ReservationTime reservationTime,
            @NonNull NumberOfUsers numberOfUsers) {
        Reservation reservation = new Reservation(new ReservationId(), accountId, practiceType);
        reservation.studioId = studioId;
        reservation.reservationTime = reservationTime;
        reservation.numberOfUsers = numberOfUsers;

        if (practiceType.equals(PracticeTypes.PERSONAL) && numberOfUsers.greatherThan(2)) {
            throw new IllegalArgumentException("個人練習の利用可能人数の上限を超えています。");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reservationTime.getReservationDate());
        calendar.add(Calendar.DATE, -1);
        calendar.add(Calendar.HOUR, 21);
        Date currentDate = new Date();
        if (practiceType.equals(PracticeTypes.PERSONAL) && currentDate.before((calendar.getTime()))) {
            throw new DomainException(DomainExceptionCodes.PersonalReservationNotStartedAccepting);
        }

        return reservation;
    }

    public static Reservation reconstruct(
            @NonNull ReservationId reservationId,
            @NonNull AccountId accountId,
            @NonNull PracticeTypes practiceType,
            @NonNull StudioId studioId,
            @NonNull ReservationTime reservationTime,
            @NonNull NumberOfUsers numberOfUsers) {
        Reservation reservation = new Reservation(reservationId, accountId, practiceType);
        reservation.studioId = studioId;
        reservation.reservationTime = reservationTime;
        reservation.numberOfUsers = numberOfUsers;
        return reservation;
    }

    public StudioId studioId() {
        return this.studioId;
    }

    public void addRentalEquipment(@NonNull RentalEquipment rentalEquipment) {
        this.rentalEquipments.add(rentalEquipment);
    }

    public boolean isDuplicated(@NonNull Reservation other) {
        return studioId.equals(other.studioId) && reservationTime.isOvarlapping(other.reservationTime);
    }
}
