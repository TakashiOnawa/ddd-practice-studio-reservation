package org.taonaw.facility.domain.model.equipment

interface EquipmentRepository {
    fun findBy(equipmentId: EquipmentId): Equipment?
    fun save(equipment: Equipment)
}