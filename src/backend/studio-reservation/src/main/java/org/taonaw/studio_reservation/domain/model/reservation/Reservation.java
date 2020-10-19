package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeRates;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccount;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountId;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;
import org.taonaw.studio_reservation.domain.shared.Assertion;
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

    private Reservation(ReservationId id) {
        Assertion.required(id, "id は必須です。");
        this.id = id;
    }

    public static Reservation create(
            StudioId studioId,
            UsageTime usageTime,
            UserCount userCount,
            UserInformation userInformation,
            PracticeTypes practiceType,
            UsageEquipments usageEquipments,
            ReservationRule reservationRule,
            LocalDateTime currentDateTime) {

        Assertion.required(studioId, "studioId は必須です。");
        Assertion.required(usageTime, "usageTime は必須です。");
        Assertion.required(userCount, "userCount は必須です。");
        Assertion.required(userInformation, "userInformation は必須です。");
        Assertion.required(practiceType, "practiceType は必須です。");
        Assertion.required(usageEquipments, "usageEquipments は必須です。");
        Assertion.required(reservationRule, "reservationRule は必須です。");
        Assertion.required(currentDateTime, "currentDateTime は必須です。");

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
            StudioId studioId,
            UsageTime usageTime,
            UserCount userCount,
            MemberAccount memberAccount,
            PracticeTypes practiceType,
            UsageEquipments usageEquipments,
            ReservationRule reservationRule,
            LocalDateTime currentDateTime) {

        Assertion.required(memberAccount, "memberAccount は必須です。");

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
            MemberAccountId memberAccountId,
            StudioId studioId,
            UserCount userCount,
            PracticeTypes practiceType,
            UsageEquipments usageEquipments,
            CancellationFeeRates cancellationFeeRates,
            LocalDateTime currentDateTime) {

        if (this.memberAccountId == null)
            throw new IllegalArgumentException("会員による予約ではないため変更できません。");

        if (!this.memberAccountId.equals(memberAccountId))
            throw new IllegalArgumentException("異なる会員による変更はできません。");

        // TODO: 各種変更処理
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
