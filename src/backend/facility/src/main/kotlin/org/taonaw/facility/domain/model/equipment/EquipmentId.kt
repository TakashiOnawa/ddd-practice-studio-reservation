package org.taonaw.facility.domain.model.equipment

import de.huxhorn.sulky.ulid.ULID

data class EquipmentId(val value: String) {
    companion object {
        fun newId(): EquipmentId {
            return EquipmentId(ULID().nextULID())
        }
    }
}