package org.taonaw.facility.domain.model.tenant

import de.huxhorn.sulky.ulid.ULID

data class TenantId(val value: String) {
    companion object {
        fun newId(): TenantId {
            return TenantId(ULID().nextULID())
        }
    }
}