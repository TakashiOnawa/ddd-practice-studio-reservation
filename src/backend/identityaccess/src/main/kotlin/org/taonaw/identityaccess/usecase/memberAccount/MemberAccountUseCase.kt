package org.taonaw.identityaccess.usecase.memberAccount

import org.springframework.stereotype.Component
import org.taonaw.identityaccess.domain.model.memberAccount.MemberAccount
import org.taonaw.identityaccess.domain.model.memberAccount.MemberAccountRepository
import org.taonaw.identityaccess.domain.shared.PasswordHashingService
import org.taonaw.identityaccess.usecase.EmailAlreadyRegistered
import org.taonaw.identityaccess.usecase.MemberAccountNotFound
import org.taonaw.identityaccess.usecase.memberAccount.changeMemberAccount.ChangeMemberAccountCommand
import org.taonaw.identityaccess.usecase.memberAccount.registerMemberAccount.RegisterMemberAccountCommand

@Component
class MemberAccountUseCase(
        val memberAccountRepository: MemberAccountRepository,
        val passwordHashingService: PasswordHashingService) {

    fun handle(command: RegisterMemberAccountCommand) {
        val memberAccount = MemberAccount.create(
                command.memberName,
                command.password.hash(passwordHashingService),
                command.contractInformation)

        save(memberAccount)
    }

    fun handle(command: ChangeMemberAccountCommand) {
        var memberAccount = memberAccountRepository.findBy(command.memberAccountId) ?: throw MemberAccountNotFound()

        memberAccount = memberAccount.change(
                command.memberName,
                command.contractInformation)

        save(memberAccount)
    }

    fun save(memberAccount: MemberAccount) {
        val saveResult = memberAccountRepository.save(memberAccount)

        if (saveResult == MemberAccountRepository.SaveResult.EMAIL_ALREADY_REGISTERED) {
            throw EmailAlreadyRegistered()
        } else if (saveResult != MemberAccountRepository.SaveResult.SUCCEEDED) {
            throw IllegalStateException("会員アカウントの登録に失敗しました。")
        }
    }
}