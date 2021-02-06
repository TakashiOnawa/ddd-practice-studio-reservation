package org.taonaw.identityaccess.domain.model.memberAccount

class MemberAccount private constructor(
        val memberAccountId: MemberAccountId,
        val memberName: MemberName,
        val contractInformation: ContractInformation) {

    companion object {
        fun create(
                memberName: MemberName,
                contractInformation: ContractInformation): MemberAccount {

            return MemberAccount(MemberAccountId.newId(), memberName, contractInformation)
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