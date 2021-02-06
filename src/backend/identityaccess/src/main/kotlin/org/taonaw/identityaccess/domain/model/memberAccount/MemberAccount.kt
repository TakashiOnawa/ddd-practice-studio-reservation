package org.taonaw.identityaccess.domain.model.memberAccount

import org.taonaw.identityaccess.domain.shared.HashedPassword

class MemberAccount private constructor(
        val memberAccountId: MemberAccountId,
        val memberName: MemberName,
        val password: HashedPassword,
        val contractInformation: ContractInformation) {

    companion object {
        fun create(
                memberName: MemberName,
                password: HashedPassword,
                contractInformation: ContractInformation): MemberAccount {

            return MemberAccount(MemberAccountId.newId(), memberName, password, contractInformation)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberAccount

        if (memberAccountId != other.memberAccountId) return false

        return true
    }

    override fun hashCode(): Int {
        return memberAccountId.hashCode()
    }
}