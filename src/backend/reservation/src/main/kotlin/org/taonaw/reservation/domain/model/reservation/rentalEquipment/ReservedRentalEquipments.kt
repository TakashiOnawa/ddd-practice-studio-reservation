package org.taonaw.reservation.domain.model.reservation.rentalEquipment

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.RentalEquipmentsOutOfStocksErr
import org.taonaw.reservation.domain.shared.exception.Err

class ReservedRentalEquipments(items: List<RentalEquipments>) {
        private val items: Map<EquipmentId, List<RentalEquipment>> = items.flatMap { it.items }.groupBy { it.equipmentId }

    fun validateUsageEquipmentsOutOfStocks(equipments: Equipments): Err? {
        val outOfStocksEquipmentIds = items.filter { it ->
            val equipment = equipments.findBy(it.key) ?: throw NoSuchElementException("機材が存在しません。equipmentId=${it.key.value}")
            equipment.equipmentStocks < it.value.sumOf { it.quantity.value }
        }.map { it.key }

        if (outOfStocksEquipmentIds.isEmpty()) return null
        return RentalEquipmentsOutOfStocksErr(outOfStocksEquipmentIds)
    }
}