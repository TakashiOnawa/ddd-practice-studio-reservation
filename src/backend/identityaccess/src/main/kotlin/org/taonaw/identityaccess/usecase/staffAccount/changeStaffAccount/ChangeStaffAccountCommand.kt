package org.taonaw.identityaccess.usecase.staffAccount.changeStaffAccount

import org.taonaw.identityaccess.domain.model.staffAccount.StaffAccountId
import org.taonaw.identityaccess.domain.model.staffAccount.StaffName
import org.taonaw.identityaccess.domain.model.staffAccount.UserId

data class ChangeStaffAccountCommand(
        val staffAccountId: StaffAccountId,
        val staffName: StaffName,
        val userId: UserId) {
}