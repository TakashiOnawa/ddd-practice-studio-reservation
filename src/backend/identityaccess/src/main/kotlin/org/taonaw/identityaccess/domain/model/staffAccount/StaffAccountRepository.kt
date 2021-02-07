package org.taonaw.identityaccess.domain.model.staffAccount

interface StaffAccountRepository {
    fun findBy(staffAccountId: StaffAccountId): StaffAccount?
    fun save(staffAccount: StaffAccount): SaveResult

    enum class SaveResult {
        SUCCEEDED,
        USER_ID_ALREADY_REGISTERED
    }
}