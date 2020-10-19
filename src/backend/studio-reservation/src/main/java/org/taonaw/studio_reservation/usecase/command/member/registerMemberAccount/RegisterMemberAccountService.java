package org.taonaw.studio_reservation.usecase.command.member.registerMemberAccount;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccount;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountRepository;
import org.taonaw.studio_reservation.domain.model.shared.PasswordEncoder;
import org.taonaw.studio_reservation.usecase.command.exception.StaffAccountDuplicatedException;

@AllArgsConstructor
public class RegisterMemberAccountService {
    @Autowired
    private final MemberAccountRepository memberAccountRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public void handle(@NonNull RegisterMemberAccountCommand command) {
        var memberAccount = MemberAccount.create(
                command.getName(),
                command.getEmailAddress(),
                command.getPlainTextPassword().createHashedPassword(passwordEncoder),
                command.getDateOfBirth(),
                command.getPhoneNumber());

        var addResult = memberAccountRepository.add(memberAccount);

        if (addResult == MemberAccountRepository.AddResults.DUPLICATED) {
            throw new StaffAccountDuplicatedException();
        }

        if (!addResult.isSucceeded()) {
            throw new IllegalStateException("会員アカウントの永続化に失敗しました。");
        }
    }
}
