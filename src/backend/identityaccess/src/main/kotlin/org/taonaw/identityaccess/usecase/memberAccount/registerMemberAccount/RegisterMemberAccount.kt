package org.taonaw.identityaccess.usecase.memberAccount.registerMemberAccount

import org.taonaw.identityaccess.domain.model.memberAccount.ContractInformation
import org.taonaw.identityaccess.domain.model.memberAccount.MemberName
import org.taonaw.identityaccess.domain.shared.PlainTextPassword

data class RegisterMemberAccount(
        val memberName: MemberName,
        val password: PlainTextPassword,
        val contractInformation: ContractInformation) {
}