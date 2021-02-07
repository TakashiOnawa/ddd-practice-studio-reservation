package org.taonaw.identityaccess.usecase.memberAccount.changeMemberAccountPassword

import org.taonaw.identityaccess.domain.model.memberAccount.MemberAccountId
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword

data class ChangeMemberAccountPasswordCommand(
        val memberAccountId: MemberAccountId,
        val oldPlainTextPassword: PlainTextPassword,
        val newPlainTextPassword: PlainTextPassword) {
}