package org.taonaw.reservation.domain.model.reservation.usageFee

data class BasicFee(val detailsList: List<UsageFeeDetails>) {

    companion object {
        fun nothing(): BasicFee {
            return BasicFee(listOf())
        }
    }

    fun hasOverlappingUsageTimeFees(): Boolean {
        return detailsList
                .any { details -> detailsList
                        .count { it.usageTime.isOverlapping(details.usageTime) } > 1 }
    }
}