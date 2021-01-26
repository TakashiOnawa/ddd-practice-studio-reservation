package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.cancellationFeeSetting.CancellationFeeSetting
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
import org.taonaw.reservation.domain.model.shared.StudioId
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeSetting
import org.taonaw.reservation.domain.shared.exception.ErrNotification
import java.time.LocalDateTime

/**
 * 予約内容
 */
data class ReservationDetails(
        val user: User,
        val studioId: StudioId,
        val usageTime: UsageTime,
        val userCount: UserCount,
        val practiceType: PracticeType,
        val rentalEquipments: RentalEquipments) {

    internal fun validateChanging(
            changingDetails: ReservationDetails,
            usageFeeSetting: UsageFeeSetting,
            cancellationFeeSetting: CancellationFeeSetting,
            changedAt: LocalDateTime): ErrNotification {

        val errNotification = ErrNotification()

        val chargedCancellationFee = cancellationFeeSetting.chargedCancellationFee(usageTime, changedAt)

        if (studioId != changingDetails.studioId && chargedCancellationFee) {
            errNotification.addErr(CanNotChangeStudioErr("キャンセル料金が発生するためスタジオを変更できません。"))
        }

        if (usageTime != changingDetails.usageTime && chargedCancellationFee) {
            if (changingDetails.usageTime.isDecrease(usageTime)) {
                errNotification.addErr(CanNotChangeUsageTimeErr("キャンセル料金が発生するため利用時間を短くすることはできません。"))
            }
        }

        if (userCount != changingDetails.userCount && chargedCancellationFee) {
            if (usageFeeSetting.isDeterminedByUserCount() && changingDetails.userCount.isDecrease(userCount)) {
                errNotification.addErr(CanNotChangeUserCountErr("キャンセル料金が発生するため利用人数を減らすことはできません。"))
            }
        }

        if (practiceType != changingDetails.practiceType && chargedCancellationFee) {
            if (practiceType == PracticeType.BAND) {
                errNotification.addErr(CanNotChangePracticeTypeErr("キャンセル料金が発生するためバンド練習から変更することはできません。"))
            }
        }

        if (rentalEquipments.isChangedQuantity(changingDetails.rentalEquipments) && chargedCancellationFee) {
            if (changingDetails.rentalEquipments.isDecreasedQuantity(rentalEquipments)) {
                errNotification.addErr(CanNotChangeRentalEquipmentsErr("キャンセル料金が発生するためレンタル機材を減らすことはできません。"))
            }
        }

        return errNotification
    }
}