package org.taonaw.identityaccess.usecase.staffAccount.changeStaffAccountPassword

import org.taonaw.identityaccess.domain.model.staffAccount.StaffAccountId
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword

data class ChangeStaffAccountPasswordCommand(
        val staffAccountId: StaffAccountId,
        val oldPlainTextPassword: PlainTextPassword,
        val newPlainTextPassword: PlainTextPassword) {
}