package org.taonaw.facility.domain.model.cancellationFee

import org.taonaw.facility.domain.model.shared.ApplicablePeriod
import org.taonaw.facility.domain.shared.exception.ErrNotification

class CancellationFee private constructor(
        val cancellationFeeId: CancellationFeeId,
        val applicablePeriod: ApplicablePeriod,
        val cancellationFeeRates: CancellationFeeRates) {

    companion object {
        fun create(applicablePeriod: ApplicablePeriod, cancellationFeeRates: CancellationFeeRates): CancellationFee {
            ErrNotification()
                    .addErr(cancellationFeeRates.validateDuplicated())
                    .addErr(cancellationFeeRates.validateRateNotRise())
                    .throwIfHasErrs("キャンセル料金レートに不備があります。")

            return CancellationFee(CancellationFeeId.newId(), applicablePeriod, cancellationFeeRates)
        }
    }

    fun change(applicablePeriod: ApplicablePeriod, cancellationFeeRates: CancellationFeeRates): CancellationFee {
        ErrNotification()
                .addErr(cancellationFeeRates.validateDuplicated())
                .addErr(cancellationFeeRates.validateRateNotRise())
                .throwIfHasErrs("キャンセル料金レートに不備があります。")

        return CancellationFee(cancellationFeeId, applicablePeriod, cancellationFeeRates)
    }

    fun isDuplicated(other: CancellationFee): Boolean {
        return this != other && applicablePeriod.isOverlapping(other.applicablePeriod)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CancellationFee

        if (cancellationFeeId != other.cancellationFeeId) return false

        return true
    }

    override fun hashCode(): Int {
        return cancellationFeeId.hashCode()
    }
}