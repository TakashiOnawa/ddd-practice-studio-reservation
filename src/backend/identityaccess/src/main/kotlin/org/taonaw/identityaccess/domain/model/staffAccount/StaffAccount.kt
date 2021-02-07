package org.taonaw.identityaccess.domain.model.staffAccount

import org.taonaw.identityaccess.domain.model.shared.HashedPassword
import org.taonaw.identityaccess.domain.model.shared.PasswordHashingService
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword

class StaffAccount private constructor(
        val staffAccountId: StaffAccountId,
        val staffName: StaffName,
        val userId: UserId,
        val password: HashedPassword) {

    companion object {
        fun create(
                staffName: StaffName,
                userId: UserId,
                plainTextPassword: PlainTextPassword,
                passwordHashingService: PasswordHashingService): StaffAccount {

            return StaffAccount(StaffAccountId.newId(), staffName, userId, plainTextPassword.hash(passwordHashingService))
        }
    }

    fun change(staffName: StaffName, userId: UserId): StaffAccount {
        return StaffAccount(staffAccountId, staffName, userId, password)
    }

    fun changePassword(
            oldPlainTextPassword: PlainTextPassword,
            newPlainTextPassword: PlainTextPassword,
            passwordHashingService: PasswordHashingService): StaffAccount {

        if (!oldPlainTextPassword.matches(password, passwordHashingService))
            StaffAccountOldPasswordDifferentErr().throwErr()

        return StaffAccount(staffAccountId, staffName, userId, newPlainTextPassword.hash(passwordHashingService))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StaffAccount

        if (staffAccountId != other.staffAccountId) return false

        return true
    }

    override fun hashCode(): Int {
        return staffAccountId.hashCode()
    }
}