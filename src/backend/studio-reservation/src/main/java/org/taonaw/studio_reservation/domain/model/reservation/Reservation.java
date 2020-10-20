package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeRates;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccount;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountId;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;
import org.taonaw.studio_reservation.domain.model.reservation.error.CanNotChangeForAlreadyStartedError;
import org.taonaw.studio_reservation.domain.model.reservation.error.CanNotChangePracticeTypeError;
import org.taonaw.studio_reservation.domain.model.reservation.error.CanNotChangeUsageStudioError;
import org.taonaw.studio_reservation.domain.model.reservation.error.CanNotChangeUsageTimeError;
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
    private PracticeTypes practiceType;
    private UsageEquipments usageEquipments;

    private Reservation(@NonNull ReservationId id) {
        this.id = id;
    }

    public static Reservation create(
            @NonNull StudioId studioId,
            @NonNull UsageTime usageTime,
            @NonNull UserCount userCount,
            @NonNull UserInformation userInformation,
            @NonNull PracticeTypes practiceType,
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
        instance.usageEquipments = usageEquipments.copy();
        return instance;
    }

    public static Reservation createByMember(
            @NonNull StudioId studioId,
            @NonNull UsageTime usageTime,
            @NonNull UserCount userCount,
            @NonNull MemberAccount memberAccount,
            @NonNull PracticeTypes practiceType,
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

    public void changeByMember(
            @NonNull MemberAccountId memberAccountId,
            @NonNull StudioId studioId,
            @NonNull UsageTime usageTime,
            @NonNull UserCount userCount,
            @NonNull PracticeTypes practiceType,
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
        this.usageEquipments = usageEquipments.copy();
    }

    public boolean isDuplicated(@NonNull Reservation other) {
        if (this.equals(other))
            return false;

        return studioId.equals(other.studioId) && usageTime.isOverlapped(other.usageTime);
    }

    public UsageTime usageTime() {
        return usageTime;
    }

    public UsageEquipments usageEquipments() {
        return usageEquipments.copy();
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
