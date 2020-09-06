package org.taonaw.studio_reservation.usecase.command.member.loginMemberAccount;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountRepository;
import org.taonaw.studio_reservation.domain.model.shared.PasswordEncoder;
import org.taonaw.studio_reservation.usecase.command.member.exception.MemberAccountNotFoundException;
import org.taonaw.studio_reservation.usecase.command.member.exception.MemberAccountUnAuthenticatedException;

@AllArgsConstructor
public class LoginMemberAccountService {
    @Autowired
    private final MemberAccountRepository memberAccountRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public void handle(LoginMemberAccountCommand command) {
        var memberAccount = memberAccountRepository.findBy(command.getEmailAddress())
                .orElseThrow(MemberAccountNotFoundException::new);

        if (!memberAccount.authenticate(command.getEmailAddress(), command.getPlainTextPassword(), passwordEncoder)) {
            throw new MemberAccountUnAuthenticatedException();
        }
    }
}
