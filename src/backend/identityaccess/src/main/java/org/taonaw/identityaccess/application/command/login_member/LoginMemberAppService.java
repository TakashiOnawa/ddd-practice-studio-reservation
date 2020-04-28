package org.taonaw.identityaccess.application.command.login_member;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.model.member.IMemberRepository;
import org.taonaw.identityaccess.domain.model.shared.EmailAddress;
import org.taonaw.identityaccess.domain.model.shared.IPasswordEncoder;
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword;
import org.taonaw.identityaccess.domain.exception.LoginMemberNotFoundException;
import org.taonaw.identityaccess.domain.exception.LoginMemberUnAuthenticatedException;

@Service
@AllArgsConstructor
public class LoginMemberAppService {
    @Autowired
    private final IMemberRepository memberRepository;
    @Autowired
    private final IPasswordEncoder passwordEncoder;

    public LoginMemberResult handle(LoginMemberCommand command) {
        var emailAddress = new EmailAddress(command.getEmailAddress());
        var plainTextPassword = new PlainTextPassword(command.getPassword());

        var member = memberRepository.findBy(emailAddress)
                .orElseThrow(() -> new LoginMemberNotFoundException(emailAddress));

        if (!member.authenticate(emailAddress, plainTextPassword, passwordEncoder)) {
            throw new LoginMemberUnAuthenticatedException(emailAddress, command.getPassword());
        }

        return LoginMemberResult.of(member);
    }
}
