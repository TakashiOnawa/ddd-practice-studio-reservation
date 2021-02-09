package org.taonaw.facility.domain.model.cancellationFee

data class CancellationFeeRate(
        val daysAgo: Int,
        val rate: Double) {

    companion object {
        const val DAYS_AGO_MIN = 0
        const val DAYS_AGO_MAX = 50
        const val RATE_MIN = 0.0
        const val RATE_MAX = 100.0
    }

    init {
        require(daysAgo in DAYS_AGO_MIN..DAYS_AGO_MAX) {
            "何日前かの値は $DAYS_AGO_MIN 以上 $DAYS_AGO_MAX 以下出なければなりません。"
        }
        require(rate in RATE_MIN..RATE_MAX) {
            "レートは $RATE_MIN % 以上 $RATE_MAX % 以下でなければなりません。"
        }
    }

    fun isDuplicated(other: CancellationFeeRate): Boolean {
        return this !== other && daysAgo == other.daysAgo
    }
}