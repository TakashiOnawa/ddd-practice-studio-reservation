package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.equipment.EquipmentId

data class RentalEquipment(
        val equipmentId: EquipmentId,
        val quantity: RentalEquipmentQuantity) {
}

class RentalEquipments private constructor(items: List<RentalEquipment>) {
    val items: List<RentalEquipment> = items.toList()

    fun equipmentIds(): List<EquipmentId> = items.map { it.equipmentId }
}