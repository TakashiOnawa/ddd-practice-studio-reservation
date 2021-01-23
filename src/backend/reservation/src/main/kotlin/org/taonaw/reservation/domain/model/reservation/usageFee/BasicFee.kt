package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.shared.Fee

data class BasicFee(
        val unitFee: Fee,
        val quantity: FeeQuantity) {
}