package org.taonaw.facility.usecase.cancellationFee.changeCancellationFee

import org.taonaw.facility.domain.model.cancellationFee.CancellationFeeId
import org.taonaw.facility.domain.model.cancellationFee.CancellationFeeRates
import org.taonaw.facility.domain.model.shared.ApplicablePeriod

data class ChangeCancellationFeeCommand(
        val cancellationFeeId: CancellationFeeId,
        val applicablePeriod: ApplicablePeriod,
        val cancellationFeeRates: CancellationFeeRates) {
}