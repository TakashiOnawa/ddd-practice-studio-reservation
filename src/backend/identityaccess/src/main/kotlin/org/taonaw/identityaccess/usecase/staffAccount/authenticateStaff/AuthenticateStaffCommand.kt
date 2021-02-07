package org.taonaw.identityaccess.usecase.staffAccount.authenticateStaff

import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword
import org.taonaw.identityaccess.domain.model.staffAccount.UserId

data class AuthenticateStaffCommand(
        val userId: UserId,
        val password: PlainTextPassword) {
}