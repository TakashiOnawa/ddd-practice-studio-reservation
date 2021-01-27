package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.reservationPolicy.MaxRentalEquipmentQuantity

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

    fun satisfy(maxRentalEquipmentQuantity: MaxRentalEquipmentQuantity): Boolean {
        return value <= maxRentalEquipmentQuantity.value
    }

    operator fun compareTo(other: RentalEquipmentQuantity): Int {
        return value.compareTo(other.value)
    }
}