package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.equipment.EquipmentId

class ReservedRentalEquipments {
    private val items: Map<EquipmentId, List<RentalEquipment>> = HashMap()

    companion object {
        fun create(rentalEquipmentsList: List<RentalEquipments>): ReservedRentalEquipments {
            // TODO: 実装する
            return ReservedRentalEquipments()
        }
    }
}