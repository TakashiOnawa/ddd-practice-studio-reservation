package org.taonaw.identityaccess.domain.model.staffAccount

import de.huxhorn.sulky.ulid.ULID

data class StaffAccountId(val value: String) {
    companion object {
        fun newId(): StaffAccountId {
            return StaffAccountId(ULID().nextULID())
        }
    }
}