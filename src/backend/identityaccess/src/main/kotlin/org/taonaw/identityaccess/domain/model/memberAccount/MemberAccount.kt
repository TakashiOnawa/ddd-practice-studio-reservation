package org.taonaw.identityaccess.domain.model.memberAccount

import org.taonaw.identityaccess.domain.model.shared.HashedPassword
import org.taonaw.identityaccess.domain.model.shared.PasswordHashingService
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword

class MemberAccount private constructor(
        val memberAccountId: MemberAccountId,
        val memberName: MemberName,
        val contractInformation: ContractInformation,
        val password: HashedPassword) {

    companion object {
        fun create(
                memberName: MemberName,
                contractInformation: ContractInformation,
                plainTextPassword: PlainTextPassword,
                passwordHashingService: PasswordHashingService): MemberAccount {

            return MemberAccount(MemberAccountId.newId(), memberName, contractInformation, plainTextPassword.hash(passwordHashingService))
        }
    }

    fun change(memberName: MemberName, contractInformation: ContractInformation): MemberAccount {
        return MemberAccount(memberAccountId, memberName, contractInformation, password)
    }

    fun changePassword(
            oldPlainTextPassword: PlainTextPassword,
            newPlainTextPassword: PlainTextPassword,
            passwordHashingService: PasswordHashingService): MemberAccount {

        if (!oldPlainTextPassword.matches(password, passwordHashingService))
            MemberAccountOldPasswordDifferentErr().throwErr()

        return MemberAccount(memberAccountId, memberName, contractInformation, newPlainTextPassword.hash(passwordHashingService))
    }

    fun authenticate(plainTextPassword: PlainTextPassword, passwordHashingService: PasswordHashingService): Boolean {
        return plainTextPassword.matches(password, passwordHashingService)
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