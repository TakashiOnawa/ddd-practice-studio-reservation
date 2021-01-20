package org.taonaw.reservation.domain.model.equipment

import org.taonaw.reservation.domain.model.shared.EquipmentId

interface EquipmentRepository {
    fun findBy(equipmentId: EquipmentId): Equipment?
    fun findBy(equipmentIds: List<EquipmentId>): Equipments
}