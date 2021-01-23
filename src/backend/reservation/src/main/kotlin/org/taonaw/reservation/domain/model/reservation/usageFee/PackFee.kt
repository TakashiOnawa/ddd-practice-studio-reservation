package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.shared.Fee

data class PackFee(
        val packName: String,
        val fee: Fee) {
}