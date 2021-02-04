package org.taonaw.reservation.domain.model.equipment

class Equipments(items: List<Equipment>) {
    private val items = items.toList()

    fun findBy(equipmentId: EquipmentId): Equipment? {
        return items.find { it.equipmentId == equipmentId }
    }
}