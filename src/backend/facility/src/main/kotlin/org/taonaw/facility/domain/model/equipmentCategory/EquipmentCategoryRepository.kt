package org.taonaw.facility.domain.model.equipmentCategory

interface EquipmentCategoryRepository {
    fun findBy(equipmentCategoryId: EquipmentCategoryId): EquipmentCategory?
    fun save(equipmentCategory: EquipmentCategory)
}