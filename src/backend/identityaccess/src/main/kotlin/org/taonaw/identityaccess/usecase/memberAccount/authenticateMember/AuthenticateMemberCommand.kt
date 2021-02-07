package org.taonaw.identityaccess.usecase.memberAccount.authenticateMember

import org.taonaw.identityaccess.domain.model.memberAccount.EmailAddress
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword

data class AuthenticateMemberCommand(
        val emailAddress: EmailAddress,
        val password: PlainTextPassword) {
}