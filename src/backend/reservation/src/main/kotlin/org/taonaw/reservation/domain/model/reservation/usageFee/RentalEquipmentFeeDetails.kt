package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.equipment.EquipmentId

data class RentalEquipmentFeeDetails(val equipmentId: EquipmentId, val usageFeeDetails: UsageFeeDetails) {
}