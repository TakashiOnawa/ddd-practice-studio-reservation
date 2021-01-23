package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.reservationPolicy.MaxRentalEquipmentQuantity
import org.taonaw.reservation.domain.model.equipment.EquipmentId

data class RentalEquipment(
        val equipmentId: EquipmentId,
        val quantity: RentalEquipmentQuantity) {

    fun satisfy(maxRentalEquipmentQuantity: MaxRentalEquipmentQuantity): Boolean {
        return quantity.satisfy(maxRentalEquipmentQuantity)
    }
}