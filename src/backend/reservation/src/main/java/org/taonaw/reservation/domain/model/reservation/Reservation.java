package org.taonaw.reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.reservation.domain.exception.CanNotCancelReservationException;
import org.taonaw.reservation.domain.exception.CanNotChangeUseTimeException;
import org.taonaw.reservation.domain.exception.CanNotChangeUserInformationException;
import org.taonaw.reservation.domain.model.cancellationfeesetting.CancellationFeeSetting;
import org.taonaw.reservation.domain.model.member.Member;
import org.taonaw.reservation.domain.model.member.MemberId;
import org.taonaw.reservation.domain.model.studio.StudioId;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

public class Reservation {
    private ReservationId reservationId;
    private StudioId studioId;
    private UseTime useTime;
    private UserInformation userInformation;
    private NumberOfUsers numberOfUsers;
    private PracticeType practiceType;
    private UseEquipments useEquipments;
    private boolean isCanceled;

    private Reservation() { }

    public static Reservation newReservation(
            @NonNull StudioId studioId,
            @NonNull UseTime useTime,
            @NonNull UserInformation userInformation,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull PracticeType practiceType,
            @NonNull UseEquipments useEquipments) {
        var reservation = new Reservation();
        reservation.reservationId = ReservationId.newId();
        reservation.studioId = studioId;
        reservation.useTime = useTime;
        reservation.userInformation = userInformation;
        reservation.numberOfUsers = numberOfUsers;
        reservation.practiceType = practiceType;
        reservation.useEquipments = useEquipments;
        return reservation;
    }

    public static Reservation reconstruct(
            @NonNull ReservationId reservationId,
            @NonNull StudioId studioId,
            @NonNull UseTime useTime,
            @NonNull UserInformation userInformation,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull PracticeType practiceType,
            @NonNull UseEquipments useEquipments) {
        var reservation = new Reservation();
        reservation.reservationId = reservationId;
        reservation.studioId = studioId;
        reservation.useTime = useTime;
        reservation.userInformation = userInformation;
        reservation.numberOfUsers = numberOfUsers;
        reservation.practiceType = practiceType;
        reservation.useEquipments = useEquipments;
        return reservation;
    }

    public static Reservation newReservationByMember(
            @NonNull StudioId studioId,
            @NonNull UseTime useTime,
            @NonNull Member member,
            @NonNull NumberOfUsers numberOfUsers,
            @NonNull PracticeType practiceType,
            @NonNull UseEquipments useEquipments) {
        return newReservation(
                studioId,
                useTime,
                member.createUserInformation(),
                numberOfUsers,
                practiceType,
                useEquipments);
    }

    public ReservationId getReservationId() {
        return reservationId;
    }

    public StudioId getStudioId() {
        return studioId;
    }

    public UseTime getUseTime() {
        return useTime;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public NumberOfUsers getNumberOfUsers() {
        return numberOfUsers;
    }

    public PracticeType getPracticeType() {
        return practiceType;
    }

    public Collection<UseEquipment> getUseEquipments() {
        return useEquipments.getUseEquipments();
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public boolean isOverlapped(@NonNull Reservation other) {
        return studioId.equals(other.studioId) && useTime.isOverlapped(other.useTime);
    }

    public void changeUseTime(
            @NonNull UseTime useTime,
            @NonNull CancellationFeeSetting cancellationFeeSetting,
            @NonNull LocalDate currentDate) {
        if (this.useTime.equals(useTime)) {
            return;
        }
        if (cancellationFeeSetting.isNotFree(this.useTime, currentDate)) {
            throw new CanNotChangeUseTimeException(reservationId);
        }
        this.useTime = useTime;
    }

    public void changeUserInformation(@NonNull UserInformation userInformation) {
        if (this.userInformation.equals(userInformation)) {
            return;
        }
        if (this.userInformation.isMembersInformation() || userInformation.isMembersInformation()) {
            throw new CanNotChangeUserInformationException(reservationId);
        }
        this.userInformation = userInformation;
    }

    public void changeNumberOfUsers(@NonNull NumberOfUsers numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public void changePracticeType(@NonNull PracticeType practiceType) {
        this.practiceType = practiceType;
    }

    public void changeUseEquipments(@NonNull UseEquipments useEquipments) {
        this.useEquipments = useEquipments;
    }

    public void cancel() {
        this.isCanceled = true;
    }

    public void cancelByMember(
            @NonNull MemberId memberId,
            @NonNull CancellationFeeSetting cancellationFeeSetting,
            @NonNull LocalDate currentDate) {

        var exceptionBuilder = new CanNotCancelReservationException.Builder(reservationId);

        if (!userInformation.getMemberId().equals(memberId)) {
            exceptionBuilder.cancelByDifferentMember(memberId);
        }
        if (cancellationFeeSetting.isNotFree(useTime, currentDate)) {
            exceptionBuilder.thereIsCancellationFee();
        }

        exceptionBuilder.throwIfErrorExists();

        this.isCanceled = true;
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
