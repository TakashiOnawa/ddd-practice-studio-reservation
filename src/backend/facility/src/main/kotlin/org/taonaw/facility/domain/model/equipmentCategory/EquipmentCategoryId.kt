package org.taonaw.facility.domain.model.equipmentCategory

import de.huxhorn.sulky.ulid.ULID

data class EquipmentCategoryId(val value: String) {
    companion object {
        fun newId(): EquipmentCategoryId {
            return EquipmentCategoryId(ULID().nextULID())
        }
    }
}