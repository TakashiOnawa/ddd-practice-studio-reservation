package org.taonaw.identityaccess.application.command.login_account;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.exception.LoginAccountNotFoundException;
import org.taonaw.identityaccess.domain.exception.LoginAccountUnAuthenticatedException;
import org.taonaw.identityaccess.domain.model.account.IAccountRepository;
import org.taonaw.identityaccess.domain.model.account.LoginId;
import org.taonaw.identityaccess.domain.model.shared.IPasswordEncoder;
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword;

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

        var account = accountRepository.findBy(loginId)
                .orElseThrow(() -> new LoginAccountNotFoundException(loginId));

        if (!account.authenticate(loginId, plainTextPassword, passwordEncoder)) {
            throw new LoginAccountUnAuthenticatedException(loginId, command.getPassword());
        }

        return LoginAccountResult.of(account);
    }
}
