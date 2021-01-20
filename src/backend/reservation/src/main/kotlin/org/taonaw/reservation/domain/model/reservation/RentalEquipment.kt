package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservationPolicy.MaxRentalEquipmentQuantity
import org.taonaw.reservation.domain.model.shared.EquipmentId

data class RentalEquipment(
        val equipmentId: EquipmentId,
        val quantity: RentalEquipmentQuantity) {

    fun satisfy(maxRentalEquipmentQuantity: MaxRentalEquipmentQuantity): Boolean {
        return quantity.satisfy(maxRentalEquipmentQuantity)
    }
}