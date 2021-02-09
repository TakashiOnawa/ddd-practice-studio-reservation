package org.taonaw.facility.usecase.cancellationFee.registerCancellationFee

import org.taonaw.facility.domain.model.cancellationFee.CancellationFeeRates
import org.taonaw.facility.domain.model.shared.ApplicablePeriod

data class RegisterCancellationFeeCommand(
        val applicablePeriod: ApplicablePeriod,
        val cancellationFeeRates: CancellationFeeRates) {
}