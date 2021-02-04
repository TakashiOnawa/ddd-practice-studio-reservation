package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipmentQuantity

data class RentalEquipmentFee(val detailsList: List<RentalEquipmentFeeDetails>) {

    companion object {
        fun nothing(): RentalEquipmentFee {
            return RentalEquipmentFee(listOf())
        }
    }
}

data class RentalEquipmentFeeDetails(
        val equipmentId: EquipmentId,
        val rentalEquipmentQuantity: RentalEquipmentQuantity,
        val usageFeeDetails: UsageFeeDetails) {
}