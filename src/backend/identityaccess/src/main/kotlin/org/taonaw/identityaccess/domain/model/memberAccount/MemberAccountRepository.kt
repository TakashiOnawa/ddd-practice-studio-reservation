package org.taonaw.identityaccess.domain.model.memberAccount

import org.taonaw.identityaccess.domain.model.staffAccount.UserId

interface MemberAccountRepository {
    fun findBy(memberAccountId: MemberAccountId): MemberAccount?
    fun findBy(userId: UserId): MemberAccount?
    fun save(memberAccount: MemberAccount): SaveResult

    enum class SaveResult {
        SUCCEEDED,
        EMAIL_ALREADY_REGISTERED
    }
}