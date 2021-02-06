package org.taonaw.identityaccess.domain.model.staffAccount

import org.taonaw.identityaccess.domain.shared.HashedPassword

class StaffAccount private constructor(
        val staffAccountId: StaffAccountId,
        val staffName: StaffName,
        val userId: UserId,
        val password: HashedPassword) {

    companion object {
        fun create(
                staffName: StaffName,
                userId: UserId,
                password: HashedPassword): StaffAccount {

            return StaffAccount(StaffAccountId.newId(), staffName, userId, password)
        }
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