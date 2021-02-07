package org.taonaw.identityaccess.usecase.memberAccount

import org.springframework.stereotype.Component
import org.taonaw.identityaccess.domain.model.memberAccount.MemberAccountRepository
import org.taonaw.identityaccess.domain.model.shared.PasswordHashingService
import org.taonaw.identityaccess.usecase.MemberAccountNotFound
import org.taonaw.identityaccess.usecase.MemberUnAuthentication
import org.taonaw.identityaccess.usecase.memberAccount.authenticateMember.AuthenticateMemberCommand

@Component
class AuthenticationMemberUserCase(
        private val memberAccountRepository: MemberAccountRepository,
        private val passwordHashingService: PasswordHashingService) {

    fun handle(command: AuthenticateMemberCommand) {
        val memberAccount = memberAccountRepository.findBy(command.userId) ?: throw MemberAccountNotFound()

        if (!memberAccount.authenticate(command.password, passwordHashingService))
            throw MemberUnAuthentication()
    }
}