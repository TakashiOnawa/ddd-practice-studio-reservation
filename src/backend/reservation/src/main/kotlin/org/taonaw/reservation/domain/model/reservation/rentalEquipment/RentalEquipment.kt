package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.equipment.EquipmentId

/**
 * レンタル機材
 */
data class RentalEquipment(
        val equipmentId: EquipmentId,
        val quantity: RentalEquipmentQuantity) {
}

class RentalEquipments private constructor(items: List<RentalEquipment>) {
    // 同じレンタル機材は数量をマージする。
    val items: List<RentalEquipment> = items
            .groupBy { it.equipmentId }
            .map { it ->
                val mergedQuantity = RentalEquipmentQuantity(it.value.sumOf { it.quantity.value })
                RentalEquipment(it.key, mergedQuantity)
            }

    fun equipmentIds(): List<EquipmentId> = items.map { it.equipmentId }
}