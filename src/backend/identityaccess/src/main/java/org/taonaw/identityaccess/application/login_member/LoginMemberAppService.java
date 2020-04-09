package org.taonaw.identityaccess.application.login_member;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.model.member.IMemberRepository;
import org.taonaw.identityaccess.domain.model.shared.EmailAddress;
import org.taonaw.identityaccess.domain.model.shared.IPasswordEncoder;
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword;
import org.taonaw.identityaccess.domain.shared.exception.DomainException;
import org.taonaw.identityaccess.domain.shared.exception.DomainExceptionCodes;

@Service
@AllArgsConstructor
public class LoginMemberAppService {
    @Autowired
    private final IMemberRepository memberRepository;
    @Autowired
    private final IPasswordEncoder passwordEncoder;

    public LoginMemberResponse handle(LoginMemberRequest request) {
        var emailAddress = new EmailAddress(request.getEmailAddress());
        var plainTextPassword = new PlainTextPassword(request.getPassword());

        var member = memberRepository.findBy(emailAddress);
        if (member.isEmpty()) {
            throw new DomainException(DomainExceptionCodes.LoginMemberNotFound);
        }

        if (!member.get().authenticate(emailAddress, plainTextPassword, passwordEncoder)) {
            throw new DomainException(DomainExceptionCodes.LoginMemberPasswordNotMatched);
        }

        return LoginMemberResponse.builder().build();
    }
}
