package org.taonaw.reservation.domain.model.equipment

interface EquipmentRepository {
    fun findBy(equipmentIds: List<EquipmentId>): Equipments
}