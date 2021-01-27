package org.taonaw.reservation.domain.model.reservation

/**
 * 利用人数
 */
data class UserCount(val value: Int) {

    companion object {
        const val MIN: Int = 1
        const val MAX: Int = 99
    }

    init {
        require(value in MIN..MAX) { "$MIN 以上 $MAX 以下でなければなりません。" }
    }

    fun isDecrease(other: UserCount): Boolean {
        return value < other.value
    }

    fun isBetween(min: Int, max: Int): Boolean {
        return value in min..max
    }

    operator fun compareTo(other: Int): Int {
        return value.compareTo(other)
    }
}