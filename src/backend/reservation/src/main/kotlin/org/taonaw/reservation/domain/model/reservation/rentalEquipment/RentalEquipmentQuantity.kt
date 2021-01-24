package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.reservationPolicy.MaxRentalEquipmentQuantity

data class RentalEquipmentQuantity(val value: Int) {

    companion object {
        private const val MIN: Int = 1
        private const val MAX: Int = 99
    }

    init {
        require(value < MIN || value > MAX) { "$MIN 以上 $MAX 以下でなければなりません。" }
    }

    fun satisfy(maxRentalEquipmentQuantity: MaxRentalEquipmentQuantity): Boolean {
        return value <= maxRentalEquipmentQuantity.value
    }
}