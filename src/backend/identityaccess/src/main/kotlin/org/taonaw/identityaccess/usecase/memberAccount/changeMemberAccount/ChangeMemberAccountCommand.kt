package org.taonaw.identityaccess.usecase.memberAccount.changeMemberAccount

import org.taonaw.identityaccess.domain.model.memberAccount.ContractInformation
import org.taonaw.identityaccess.domain.model.memberAccount.MemberAccountId
import org.taonaw.identityaccess.domain.model.memberAccount.MemberName

class ChangeMemberAccountCommand(
        val memberAccountId: MemberAccountId,
        val memberName: MemberName,
        val contractInformation: ContractInformation) {
}