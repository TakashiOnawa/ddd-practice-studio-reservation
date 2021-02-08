package org.taonaw.facility.domain.model.studio

import de.huxhorn.sulky.ulid.ULID

data class StudioId(val value: String) {
    companion object {
        fun newId(): StudioId {
            return StudioId(ULID().nextULID())
        }
    }
}