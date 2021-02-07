package org.taonaw.identityaccess.usecase.memberAccount.authenticateMember

import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword
import org.taonaw.identityaccess.domain.model.staffAccount.UserId

data class AuthenticateMemberCommand(
        val userId: UserId,
        val password: PlainTextPassword) {
}