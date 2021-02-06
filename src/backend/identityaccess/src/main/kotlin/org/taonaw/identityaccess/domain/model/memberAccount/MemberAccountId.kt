package org.taonaw.identityaccess.domain.model.memberAccount

import de.huxhorn.sulky.ulid.ULID

data class MemberAccountId(val value: String) {
    companion object {
        fun newId(): MemberAccountId {
            return MemberAccountId(ULID().nextULID())
        }
    }
}