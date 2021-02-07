package org.taonaw.identityaccess.usecase.staffAccount

import org.springframework.stereotype.Component
import org.taonaw.identityaccess.domain.model.staffAccount.StaffAccount
import org.taonaw.identityaccess.domain.model.staffAccount.StaffAccountRepository
import org.taonaw.identityaccess.domain.shared.PasswordHashingService
import org.taonaw.identityaccess.usecase.StaffAccountNotFound
import org.taonaw.identityaccess.usecase.UserIdAlreadyRegistered
import org.taonaw.identityaccess.usecase.staffAccount.changeStaffAccount.ChangeStaffAccountCommand
import org.taonaw.identityaccess.usecase.staffAccount.registerStaffAccount.RegisterStaffAccountCommand

@Component
class StaffAccountUseCase(
        private val staffAccountRepository: StaffAccountRepository,
        private val passwordHashingService: PasswordHashingService) {

    fun handle(command: RegisterStaffAccountCommand) {
        val staffAccount = StaffAccount.create(
                command.staffName,
                command.userId,
                command.password.hash(passwordHashingService))

        save(staffAccount)
    }

    fun handle(command: ChangeStaffAccountCommand) {
        var staffAccount = staffAccountRepository.findBy(command.staffAccountId) ?: throw StaffAccountNotFound()

        staffAccount = staffAccount.change(command.staffName, command.userId)

        save(staffAccount)
    }

    fun save(staffAccount: StaffAccount) {
        val saveResult = staffAccountRepository.save(staffAccount)

        if (saveResult == StaffAccountRepository.SaveResult.USER_ID_ALREADY_REGISTERED) {
            throw UserIdAlreadyRegistered()
        } else if (saveResult != StaffAccountRepository.SaveResult.SUCCEEDED) {
            throw IllegalStateException("スタッフアカウントの登録に失敗しました。")
        }
    }
}