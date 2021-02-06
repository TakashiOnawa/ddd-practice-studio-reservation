package org.taonaw.identityaccess.domain.model.memberAccount

interface MemberAccountRepository {
    fun save(memberAccount: MemberAccount): SaveResult

    enum class SaveResult {
        SUCCEEDED,
        EMAIL_ALREADY_REGISTERED
    }
}