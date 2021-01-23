package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.reservationPolicy.MaxRentalEquipmentQuantity
import org.taonaw.reservation.domain.model.equipment.EquipmentId

class RentalEquipments private constructor(items: List<RentalEquipment>) {
    private val items = items.toList()

    fun equipmentIds(): List<EquipmentId> = items.map { it.equipmentId }

    fun notSatisfyEquipments(maxRentalEquipmentQuantities: Map<EquipmentId, MaxRentalEquipmentQuantity>): List<EquipmentId> {
        val notSatisfyEquipmentIds = mutableListOf<EquipmentId>()

        for (item in items) {
            maxRentalEquipmentQuantities[item.equipmentId]?.let {
                if (!item.satisfy(it))
                    notSatisfyEquipmentIds.add(item.equipmentId)
            }
        }

        return notSatisfyEquipmentIds
    }
}