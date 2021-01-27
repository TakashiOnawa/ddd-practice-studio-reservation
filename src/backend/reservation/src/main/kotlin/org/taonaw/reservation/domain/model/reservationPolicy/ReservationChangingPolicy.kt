package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.cancellationFeeSetting.CancellationFeeSetting
import org.taonaw.reservation.domain.model.reservation.PracticeType
import org.taonaw.reservation.domain.model.reservation.ReservationDetails
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeSetting
import org.taonaw.reservation.domain.shared.exception.Err
import org.taonaw.reservation.domain.shared.exception.ErrNotification
import java.time.LocalDateTime

/**
 * 予約変更ポリシー
 */
class
ReservationChangingPolicy(
        private val usageFeeSetting: UsageFeeSetting,
        private val cancellationFeeSetting: CancellationFeeSetting) {

    internal fun validate(before: ReservationDetails, after: ReservationDetails, changedAt: LocalDateTime): ErrNotification {
        val chargedCancellationFee = cancellationFeeSetting.chargedCancellationFee(before.usageTime, changedAt)
        return ErrNotification()
                .addErr(validateStudio(before, after, chargedCancellationFee))
                .addErr(validateUsageTime(before, after, chargedCancellationFee))
                .addErr(validateUserCount(before, after, chargedCancellationFee))
                .addErr(validatePracticeType(before, after, chargedCancellationFee))
                .addErr(validateRentalEquipments(before, after, chargedCancellationFee))
    }

    private fun validateStudio(before: ReservationDetails, after: ReservationDetails, chargedCancellationFee: Boolean): Err? {
        if (before.studioId != after.studioId && chargedCancellationFee) {
            return CanNotChangeStudioErr("キャンセル料金が発生するためスタジオを変更できません。")
        }
        return null
    }

    private fun validateUsageTime(before: ReservationDetails, after: ReservationDetails, chargedCancellationFee: Boolean): Err? {
        if (before.usageTime != after.usageTime && chargedCancellationFee) {
            if (after.usageTime.isDecrease(before.usageTime)) {
                return CanNotChangeUsageTimeErr("キャンセル料金が発生するため利用時間を短くすることはできません。")
            }
        }
        return null
    }

    private fun validateUserCount(before: ReservationDetails, after: ReservationDetails, chargedCancellationFee: Boolean): Err? {
        if (before.userCount != after.userCount && chargedCancellationFee) {
            if (usageFeeSetting.isDeterminedByUserCount() && after.userCount.isDecrease(before.userCount)) {
                return CanNotChangeUserCountErr("キャンセル料金が発生するため利用人数を減らすことはできません。")
            }
        }
        return null
    }

    private fun validatePracticeType(before: ReservationDetails, after: ReservationDetails, chargedCancellationFee: Boolean): Err? {
        if (before.practiceType != after.practiceType && chargedCancellationFee) {
            if (before.practiceType == PracticeType.BAND) {
                return CanNotChangePracticeTypeErr("キャンセル料金が発生するためバンド練習から変更することはできません。")
            }
        }
        return null
    }

    private fun validateRentalEquipments(before: ReservationDetails, after: ReservationDetails, chargedCancellationFee: Boolean): Err? {
        if (before.rentalEquipments.isChangedQuantity(after.rentalEquipments) && chargedCancellationFee) {
            if (after.rentalEquipments.isDecreasedQuantity(before.rentalEquipments)) {
                return CanNotChangeRentalEquipmentsErr("キャンセル料金が発生するためレンタル機材を減らすことはできません。")
            }
        }
        return null
    }
}