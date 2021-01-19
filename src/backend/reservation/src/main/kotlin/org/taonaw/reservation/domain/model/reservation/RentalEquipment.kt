package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.shared.EquipmentId

data class RentalEquipment(
        val equipmentId: EquipmentId,
        val quantity: RentalEquipmentQuantity) {
}