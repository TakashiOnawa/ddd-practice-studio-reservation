package org.taonaw.identityaccess.usecase.memberAccount

import org.springframework.stereotype.Component
import org.taonaw.identityaccess.domain.model.memberAccount.MemberAccount
import org.taonaw.identityaccess.domain.model.memberAccount.MemberAccountRepository
import org.taonaw.identityaccess.domain.shared.PasswordHashingService
import org.taonaw.identityaccess.usecase.EmailAlreadyRegistered
import org.taonaw.identityaccess.usecase.memberAccount.registerMemberAccount.RegisterMemberAccount

@Component
class MemberAccountUseCase(
        val memberAccountRepository: MemberAccountRepository,
        val passwordHashingService: PasswordHashingService) {

    fun handle(command: RegisterMemberAccount) {
        val memberAccount = MemberAccount.create(
                command.memberName,
                command.password.hash(passwordHashingService),
                command.contractInformation)

        val saveResult = memberAccountRepository.save(memberAccount)

        if (saveResult == MemberAccountRepository.SaveResult.EMAIL_ALREADY_REGISTERED) {
            throw EmailAlreadyRegistered()
        } else if (saveResult != MemberAccountRepository.SaveResult.SUCCEEDED) {
            throw IllegalStateException("会員アカウントの登録に失敗しました。")
        }
    }
}