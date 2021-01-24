package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipment
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments

/**
 * 最大レンタル機材数
 */
data class MaxRentalEquipmentQuantity(val value: Int) {

    fun isSatisfiedBy(rentalEquipment: RentalEquipment): Boolean {
        return rentalEquipment.quantity.value <= value
    }
}

data class MaxRentalEquipmentQuantities(
        private val maxRentalEquipmentQuantities: Map<EquipmentId, MaxRentalEquipmentQuantity>) {

    fun isSatisfiedBy(rentalEquipments: RentalEquipments): Boolean {
        return rentalEquipments.items.filter {
            val maxRentalEquipmentQuantity = maxRentalEquipmentQuantities[it.equipmentId] ?: throw Exception()
            return !maxRentalEquipmentQuantity.isSatisfiedBy(it)
        }.any()
    }
}