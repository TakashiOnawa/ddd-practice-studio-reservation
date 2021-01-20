package org.taonaw.reservation.domain.model.equipment

interface EquipmentRepository {
    fun findBy(equipmentId: EquipmentId): Equipment?
    fun findBy(equipmentIds: List<EquipmentId>): Equipments
}