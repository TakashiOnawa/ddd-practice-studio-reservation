package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.equipment.EquipmentId

class RentalEquipments private constructor(items: List<RentalEquipment>) {
    val items = items.toList()

    fun equipmentIds(): List<EquipmentId> = items.map { it.equipmentId }
}