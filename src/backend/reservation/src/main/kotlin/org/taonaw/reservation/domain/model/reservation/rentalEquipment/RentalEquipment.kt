package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.equipment.EquipmentId

/**
 * レンタル機材
 */
class RentalEquipment(
        val equipmentId: EquipmentId,
        val quantity: RentalEquipmentQuantity) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RentalEquipment

        if (equipmentId != other.equipmentId) return false

        return true
    }

    override fun hashCode(): Int {
        return equipmentId.hashCode()
    }
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

    fun isChangedQuantity(other: RentalEquipments): Boolean {
        TODO("実装する")
    }

    fun isDecreasedQuantity(other: RentalEquipments): Boolean {
        TODO("実装する")
    }
}