package org.taonaw.facility.domain.model.room

import de.huxhorn.sulky.ulid.ULID

data class RoomId(val value: String) {
    companion object {
        fun newId(): RoomId {
            return RoomId(ULID().nextULID())
        }
    }
}