package org.taonaw.reservation.domain.model.reservation.TotalUsageFee

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.model.shared.Fee

data class RentalEquipmentFee(
        val equipmentId: EquipmentId,
        val fee: Fee,
        val feeQuantity: FeeQuantity) {
}