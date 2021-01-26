package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.cancellationFeeSetting.CancellationFeeSetting
import org.taonaw.reservation.domain.model.reservation.PracticeType
import org.taonaw.reservation.domain.model.reservation.ReservationDetails
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeSetting
import org.taonaw.reservation.domain.shared.exception.ErrNotification
import java.time.LocalDateTime

/**
 * 予約変更ポリシー
 */
class ReservationChangingPolicy(
        private val usageFeeSetting: UsageFeeSetting,
        private val cancellationFeeSetting: CancellationFeeSetting) {

    internal fun validate(before: ReservationDetails, after: ReservationDetails, changedAt: LocalDateTime): ErrNotification {
        val errNotification = ErrNotification()

        val chargedCancellationFee = cancellationFeeSetting.chargedCancellationFee(before.usageTime, changedAt)

        if (before.studioId != after.studioId && chargedCancellationFee) {
            errNotification.addErr(CanNotChangeStudioErr("キャンセル料金が発生するためスタジオを変更できません。"))
        }

        if (before.usageTime != after.usageTime && chargedCancellationFee) {
            if (after.usageTime.isDecrease(before.usageTime)) {
                errNotification.addErr(CanNotChangeUsageTimeErr("キャンセル料金が発生するため利用時間を短くすることはできません。"))
            }
        }

        if (before.userCount != after.userCount && chargedCancellationFee) {
            if (usageFeeSetting.isDeterminedByUserCount() && after.userCount.isDecrease(before.userCount)) {
                errNotification.addErr(CanNotChangeUserCountErr("キャンセル料金が発生するため利用人数を減らすことはできません。"))
            }
        }

        if (before.practiceType != after.practiceType && chargedCancellationFee) {
            if (before.practiceType == PracticeType.BAND) {
                errNotification.addErr(CanNotChangePracticeTypeErr("キャンセル料金が発生するためバンド練習から変更することはできません。"))
            }
        }

        if (before.rentalEquipments.isChangedQuantity(after.rentalEquipments) && chargedCancellationFee) {
            if (after.rentalEquipments.isDecreasedQuantity(before.rentalEquipments)) {
                errNotification.addErr(CanNotChangeRentalEquipmentsErr("キャンセル料金が発生するためレンタル機材を減らすことはできません。"))
            }
        }

        return errNotification
    }
}