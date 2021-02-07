package org.taonaw.identityaccess.usecase.staffAccount

import org.springframework.stereotype.Component
import org.taonaw.identityaccess.domain.model.shared.PasswordHashingService
import org.taonaw.identityaccess.domain.model.staffAccount.StaffAccountRepository
import org.taonaw.identityaccess.usecase.StaffAccountNotFound
import org.taonaw.identityaccess.usecase.StaffUnAuthentication
import org.taonaw.identityaccess.usecase.staffAccount.authenticateStaff.AuthenticateStaffCommand

@Component
class AuthenticationStaffUseCase(
        val staffAccountRepository: StaffAccountRepository,
        val passwordHashingService: PasswordHashingService) {

    fun handle(command: AuthenticateStaffCommand) {
        val staffAccount = staffAccountRepository.findBy(command.userId) ?: throw StaffAccountNotFound()

        if (!staffAccount.authenticate(command.userId, command.password, passwordHashingService))
            throw StaffUnAuthentication()
    }
}