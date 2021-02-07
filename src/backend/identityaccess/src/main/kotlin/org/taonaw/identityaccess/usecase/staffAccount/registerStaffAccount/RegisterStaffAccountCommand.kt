package org.taonaw.identityaccess.usecase.staffAccount.registerStaffAccount

import org.taonaw.identityaccess.domain.model.staffAccount.StaffName
import org.taonaw.identityaccess.domain.model.staffAccount.UserId
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword

data class RegisterStaffAccountCommand(
        val staffName: StaffName,
        val userId: UserId,
        val plainTextPassword: PlainTextPassword) {
}