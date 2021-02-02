package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipmentQuantity

data class RentalEquipmentFeeDetails(
        val equipmentId: EquipmentId,
        val rentalEquipmentQuantity: RentalEquipmentQuantity,
        val usageFeeDetails: UsageFeeDetails) {
}