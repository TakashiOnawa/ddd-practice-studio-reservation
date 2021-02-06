package org.taonaw.identityaccess.usecase.staffAccount

import org.springframework.stereotype.Component
import org.taonaw.identityaccess.domain.model.staffAccount.StaffAccount
import org.taonaw.identityaccess.domain.model.staffAccount.StaffAccountRepository
import org.taonaw.identityaccess.domain.shared.PasswordHashingService
import org.taonaw.identityaccess.usecase.UserIdAlreadyRegistered
import org.taonaw.identityaccess.usecase.staffAccount.registerStaffAccount.RegisterStaffAccountCommand
import java.lang.IllegalStateException

@Component
class StaffAccountUseCase(
        private val staffAccountRepository: StaffAccountRepository,
        private val passwordHashingService: PasswordHashingService) {

    fun handle(command: RegisterStaffAccountCommand) {
        val staffAccount = StaffAccount.create(
                command.staffName,
                command.userId,
                command.password.hash(passwordHashingService))

        val saveResult = staffAccountRepository.save(staffAccount)

        if (saveResult == StaffAccountRepository.SaveResult.USER_ID_ALREADY_REGISTERED) {
            throw UserIdAlreadyRegistered()
        } else if (saveResult != StaffAccountRepository.SaveResult.SUCCEEDED) {
            throw IllegalStateException("スタッフアカウントの登録に失敗しました。")
        }
    }
}