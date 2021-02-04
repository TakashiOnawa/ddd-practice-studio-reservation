package org.taonaw.reservation.domain.model.reservation.rentalEquipment

/**
 * レンタル機材数量
 */
data class RentalEquipmentQuantity(val value: Int) {

    companion object {
        const val MIN: Int = 1
        const val MAX: Int = 99
    }

    init {
        require(value in MIN..MAX) { "$MIN 以上 $MAX 以下でなければなりません。" }
    }

    operator fun compareTo(other: RentalEquipmentQuantity): Int {
        return value.compareTo(other.value)
    }
}