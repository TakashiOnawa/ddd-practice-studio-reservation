package org.taonaw.reservation.domain.model.equipment

class Equipment(
        val equipmentId: EquipmentId,
        private val equipmentStocks: EquipmentStocks) {

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