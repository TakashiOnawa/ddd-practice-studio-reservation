package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.equipment.EquipmentId

data class RentalEquipment(
        val equipmentId: EquipmentId,
        val quantity: RentalEquipmentQuantity) {
}