package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.reservationPolicy.MaxRentalEquipmentQuantity

data class RentalEquipmentQuantity(val value: Int) {

    fun satisfy(maxRentalEquipmentQuantity: MaxRentalEquipmentQuantity): Boolean {
        return value <= maxRentalEquipmentQuantity.value
    }
}