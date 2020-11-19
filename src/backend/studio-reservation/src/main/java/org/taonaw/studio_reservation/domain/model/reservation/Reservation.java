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

    public void changeByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull StudioId studioId,
            @NonNull UsageTime usageTime,
            @NonNull UserCount userCount,
            @NonNull PracticeType practiceType,
            @NonNull UsageEquipments usageEquipments,
            @NonNull CancellationFeeRates cancellationFeeRates,
            @NonNull LocalDateTime currentDateTime) {

        if (this.memberAccountId == null)
            throw new IllegalArgumentException("会員による予約ではないため変更できません。");
        if (!this.memberAccountId.equals(memberAccountId))
            throw new IllegalArgumentException("異なる会員による変更はできません。");

        if (this.usageTime.isPassed(currentDateTime))
            new CanNotChangeForAlreadyStartedError().throwError();

        var isCancellationFeeFree = this.usageTime.isCancellationFeeFree(cancellationFeeRates, currentDateTime);
        var errorNotification = new ErrorNotification();
        if (!this.studioId.equals(studioId) && !isCancellationFeeFree)
            errorNotification.addError(new CanNotChangeUsageStudioError());
        if (!this.usageTime.equals(usageTime) && !isCancellationFeeFree)
            errorNotification.addError(new CanNotChangeUsageTimeError());
        if (!this.practiceType.equals(practiceType) && !isCancellationFeeFree)
            errorNotification.addError(new CanNotChangePracticeTypeError());
        errorNotification.throwIfHasErrors("予約を変更できません。");

        this.studioId = studioId;
        this.usageTime = usageTime;
        this.userCount = userCount;
        this.practiceType = practiceType;
        this.usageEquipments = usageEquipments;
    }

    public void cancelByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull LocalDateTime canceledOn,
            @NonNull CancellationFeeRates cancellationFeeRates) {

        if (this.memberAccountId == null)
            throw new IllegalArgumentException("会員による予約ではないためキャンセルできません。");
        if (!this.memberAccountId.equals(memberAccountId))
            throw new IllegalArgumentException("異なる会員によるキャンセルはできません。");
        if (status == ReservationStatus.CANCELED)
            throw new IllegalStateException("既にキャンセルされています。");

        if (this.usageTime.isPassed(canceledOn))
            new CanNotCancelForAlreadyStartedError().throwError();

        var isCancellationFeeFree = this.usageTime.isCancellationFeeFree(cancellationFeeRates, canceledOn);
        var errorNotification = new ErrorNotification();
        if (!isCancellationFeeFree)
            errorNotification.addError(new CanNotCancelForCancellationFeeNotFreeError());
        errorNotification.throwIfHasErrors("予約をキャンセルできません。");

        status = ReservationStatus.CANCELED;
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
