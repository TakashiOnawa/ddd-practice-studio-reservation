package org.taonaw.identityaccess.application.loginaccount;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.model.accounts.*;
import org.taonaw.identityaccess.domain.shared.exception.DomainException;
import org.taonaw.identityaccess.domain.shared.exception.DomainExceptionCodes;

@Service
@AllArgsConstructor
public class LoginAccountAppService {

    @Autowired
    private final IAccountRepository accountRepository;
    @Autowired
    private final IPasswordEncoder passwordEncoder;

    public LoginAccountResponse handle(LoginAccountRequest request) {

        var loginId = new LoginId(request.getLoginId());
        var plainTextPassword = new PlainTextPassword(request.getPassword());

        var account = accountRepository.find(loginId);
        if (account.isEmpty()) {
            throw new DomainException(DomainExceptionCodes.LoginAccountNotFound);
        }

        if (!account.get().authenticate(loginId, plainTextPassword, passwordEncoder)) {
            throw new DomainException(DomainExceptionCodes.LoginAccountPasswordNotMatched);
        }

        return LoginAccountResponse.builder()
                .loginId(request.getLoginId())
                .build();
    }
}
