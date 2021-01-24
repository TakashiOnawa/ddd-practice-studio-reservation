package org.taonaw.reservation.domain.model.reservation

/**
 * 利用人数
 */
data class UserCount(val value: Int) {

    companion object {
        private const val MIN: Int = 1
        private const val MAX: Int = 99
    }

    init {
        require(value < MIN || value > MAX) { "$MIN 以上 $MAX 以下でなければなりません。" }
    }
}