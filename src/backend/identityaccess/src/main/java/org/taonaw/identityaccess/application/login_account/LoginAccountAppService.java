package org.taonaw.identityaccess.application.login_account;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.model.account.*;
import org.taonaw.identityaccess.domain.model.shared.IPasswordEncoder;
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword;
import org.taonaw.identityaccess.domain.shared.exception.DomainException;
import org.taonaw.identityaccess.domain.shared.exception.DomainExceptionCodes;

@Service
@AllArgsConstructor
public class LoginAccountAppService {
    @Autowired
    private final IAccountRepository accountRepository;
    @Autowired
    private final IPasswordEncoder passwordEncoder;

    public LoginAccountResult handle(LoginAccountCommand command) {
        var loginId = new LoginId(command.getLoginId());
        var plainTextPassword = new PlainTextPassword(command.getPassword());

        var account = accountRepository.findBy(loginId);
        if (account.isEmpty()) {
            throw new DomainException(DomainExceptionCodes.LoginAccountNotFound);
        }

        if (!account.get().authenticate(loginId, plainTextPassword, passwordEncoder)) {
            throw new DomainException(DomainExceptionCodes.LoginAccountPasswordNotMatched);
        }

        return LoginAccountResult.builder()
                .loginId(command.getLoginId())
                .build();
    }
}
