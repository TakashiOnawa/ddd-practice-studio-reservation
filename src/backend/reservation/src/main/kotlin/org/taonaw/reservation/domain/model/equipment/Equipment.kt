package org.taonaw.reservation.domain.model.equipment

import org.taonaw.reservation.domain.model.shared.Fee

class Equipment(
        val equipmentId: EquipmentId,
        val equipmentStocks: EquipmentStocks,
        val rentalFee: Fee) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Equipment

        if (equipmentId != other.equipmentId) return false

        return true
    }

    override fun hashCode(): Int {
        return equipmentId.hashCode()
    }
}