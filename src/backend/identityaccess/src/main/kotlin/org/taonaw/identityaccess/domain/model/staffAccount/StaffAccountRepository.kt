package org.taonaw.identityaccess.domain.model.staffAccount

interface StaffAccountRepository {
    fun save(staffAccount: StaffAccount): SaveResult

    enum class SaveResult {
        SUCCEEDED,
        USER_ID_REGISTERED
    }
}