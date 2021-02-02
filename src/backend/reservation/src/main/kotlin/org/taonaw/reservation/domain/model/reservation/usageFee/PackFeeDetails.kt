package org.taonaw.reservation.domain.model.reservation.usageFee

data class PackFeeDetails(
        val packName: String,
        val detailsList: List<UsageFeeDetails>) {
}