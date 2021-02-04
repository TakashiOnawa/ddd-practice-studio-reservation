package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.equipment.EquipmentId

class RentalEquipments (items: List<RentalEquipment>) {
    // 同じレンタル機材は数量をマージする。
    val items: List<RentalEquipment> = items
            .groupBy { it.equipmentId }
            .map { it ->
                val mergedQuantity = RentalEquipmentQuantity(it.value.sumOf { it.quantity.value })
                RentalEquipment(it.key, mergedQuantity)
            }

    fun equipmentIds(): List<EquipmentId> = items.map { it.equipmentId }

    fun isNotChangeQuantity(other: RentalEquipments): Boolean {
        return items.size == other.items.size &&
                items.all { other.items.find { otherItem -> otherItem == it }?.quantity == it.quantity }
    }

    fun isChangedQuantity(other: RentalEquipments): Boolean {
        return !isNotChangeQuantity(other)
    }

    fun isDecreasedQuantity(other: RentalEquipments): Boolean {
        return other.items.any() {
            val thisItem = items.find { thisItem -> thisItem == it }
            thisItem == null || thisItem.quantity < it.quantity
        }
    }
}