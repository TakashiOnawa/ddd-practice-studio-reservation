package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeRates;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccount;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountId;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeType;
import org.taonaw.studio_reservation.domain.model.reservation.error.*;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation {
    private final ReservationId id;
    private StudioId studioId;
    private MemberAccountId memberAccountId;
    private UsageTime usageTime;
    private UserCount userCount;
    private UserInformation userInformation;
    private PracticeType practiceType;
    private UsageEquipments usageEquipments;
    private ReservationStatus status = ReservationStatus.RESERVED;
    private long version = 0;

    private Reservation(@NonNull ReservationId id) {
        this.id = id;
    }

    public static Reservation create(
            @NonNull StudioId studioId,
            @NonNull UsageTime usageTime,
            @NonNull UserCount userCount,
            @NonNull UserInformation userInformation,
            @NonNull PracticeType practiceType,
            @NonNull UsageEquipments usageEquipments,
            @NonNull ReservationRule reservationRule,
            @NonNull LocalDateTime currentDateTime) {

        var errorNotification = new ErrorNotification();
        errorNotification.addError(reservationRule.validateOpeningHour(usageTime));
        errorNotification.addError(reservationRule.validateReservationStartDateTime(usageTime, currentDateTime));
        errorNotification.addError(reservationRule.validateUserMaxCount(userCount));
        errorNotification.addError(reservationRule.validateStartTime(usageTime));
        errorNotification.addError(reservationRule.validateEquipmentMaxUsableCount(usageEquipments));
        errorNotification.throwIfHasErrors("予約内容に不備があります。");

        var instance = new Reservation(ReservationId.newId());
        instance.studioId = studioId;
        instance.usageTime = usageTime;
        instance.userCount = userCount;
        instance.userInformation = userInformation;
        instance.practiceType = practiceType;
        instance.usageEquipments = usageEquipments;
        return instance;
    }

    public static Reservation createByMember(
            @NonNull StudioId studioId,
            @NonNull UsageTime usageTime,
            @NonNull UserCount userCount,
            @NonNull MemberAccount memberAccount,
            @NonNull PracticeType practiceType,
            @NonNull UsageEquipments usageEquipments,
            @NonNull ReservationRule reservationRule,
            @NonNull LocalDateTime currentDateTime) {

        return create(
                studioId,
                usageTime,
                userCount,
                new UserInformation(memberAccount.getName(), memberAccount.getPhoneNumber()),
                practiceType,
                usageEquipments,
                reservationRule,
                currentDateTime);
    }

    public static Reservation reconstruct(
            ReservationId id,
            StudioId studioId,
            MemberAccountId memberAccountId,
            UsageTime usageTime,
            UserCount userCount,
            UserInformation userInformation,
            PracticeType practiceType,
            UsageEquipments usageEquipments,
            ReservationStatus status,
            long version) {

        var instance = new Reservation(id);
        instance.studioId = studioId;
        instance.memberAccountId = memberAccountId;
        instance.usageTime = usageTime;
        instance.userCount = userCount;
        instance.userInformation = userInformation;
        instance.practiceType = practiceType;
        instance.usageEquipments = usageEquipments;
        instance.status = status;
        instance.version = version;
        return instance;
    }

    public void changeStudioByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull StudioId studioId,
            @NonNull CancellationFeeRates cancellationFeeRates,
            @NonNull LocalDateTime currentDateTime,
            @NonNull ErrorNotification errorNotification) {

        validateChangeByMember(memberAccountId, currentDateTime);
        if (!this.studioId.equals(studioId)) {
            if (isCancellationFeeApplied(cancellationFeeRates, currentDateTime))
                errorNotification.addError(new CanNotChangeUsageStudioError());
            if (errorNotification.noErrors())
                this.studioId = studioId;
        }
    }

    public void changeUsageTimeByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull UsageTime usageTime,
            @NonNull CancellationFeeRates cancellationFeeRates,
            @NonNull LocalDateTime currentDateTime,
            @NonNull ErrorNotification errorNotification) {

        validateChangeByMember(memberAccountId, currentDateTime);
        if (!this.usageTime.equals(usageTime)) {
            if (isCancellationFeeApplied(cancellationFeeRates, currentDateTime))
                errorNotification.addError(new CanNotChangeUsageTimeError());
            if (errorNotification.noErrors())
                this.usageTime = usageTime;
        }
    }

    public void changeUserCountByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull UserCount userCount,
            @NonNull LocalDateTime currentDateTime) {

        validateChangeByMember(memberAccountId, currentDateTime);
        this.userCount = userCount;
    }

    public void changePracticeTypeByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull PracticeType practiceType,
            @NonNull CancellationFeeRates cancellationFeeRates,
            @NonNull LocalDateTime currentDateTime,
            @NonNull ErrorNotification errorNotification) {

        validateChangeByMember(memberAccountId, currentDateTime);
        if (!this.practiceType.equals(practiceType)) {
            if (isCancellationFeeApplied(cancellationFeeRates, currentDateTime))
                errorNotification.addError(new CanNotChangePracticeTypeError());
            if (errorNotification.noErrors())
                this.practiceType = practiceType;
        }
    }

    public void changeUsageEquipmentsByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull UsageEquipments usageEquipments,
            @NonNull LocalDateTime currentDateTime) {

        validateChangeByMember(memberAccountId, currentDateTime);
        this.usageEquipments = usageEquipments;
    }

    private void validateChangeByMember(MemberAccountId memberAccountId, LocalDateTime currentDateTime) {
        validateOperationByMember(memberAccountId);
        if (this.usageTime.isPassed(currentDateTime))
            new CanNotChangeForAlreadyStartedError().throwError();
    }

    public void cancelByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull LocalDateTime canceledOn,
            @NonNull CancellationFeeRates cancellationFeeRates,
            @NonNull ErrorNotification errorNotification) {

        validateOperationByMember(memberAccountId);
        if (status == ReservationStatus.CANCELED)
            throw new IllegalStateException("既にキャンセルされています。");
        if (this.usageTime.isPassed(canceledOn))
            new CanNotCancelForAlreadyStartedError().throwError();

        if (isCancellationFeeApplied(cancellationFeeRates, canceledOn))
            errorNotification.addError(new CanNotCancelForCancellationFeeNotFreeError());
        if (errorNotification.noErrors())
            status = ReservationStatus.CANCELED;
    }

    private void validateOperationByMember(MemberAccountId memberAccountId) {
        if (this.memberAccountId == null)
            throw new IllegalArgumentException("会員による予約ではないため変更できません。");
        if (!this.memberAccountId.equals(memberAccountId))
            throw new IllegalArgumentException("異なる会員による変更はできません。");
    }

    private boolean isCancellationFeeApplied(CancellationFeeRates cancellationFeeRates, LocalDateTime currentDateTime) {
        return !this.usageTime.isCancellationFeeFree(cancellationFeeRates, currentDateTime);
    }

    public boolean isDuplicated(@NonNull Reservation other) {
        if (this.equals(other))
            return false;

        return status != ReservationStatus.CANCELED &&
                studioId.equals(other.studioId) &&
                usageTime.isOverlapped(other.usageTime);
    }

    public boolean isChangedByOther(long version) {
        return this.version != version;
    }

    public UsageTime usageTime() {
        return usageTime;
    }

    public UsageEquipments usageEquipments() {
        return usageEquipments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
