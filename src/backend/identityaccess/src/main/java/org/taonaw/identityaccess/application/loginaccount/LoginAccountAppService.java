package org.taonaw.identityaccess.application.loginaccount;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.model.accounts.IAccountRepository;
import org.taonaw.identityaccess.domain.model.accounts.LoginId;
import org.taonaw.identityaccess.domain.model.accounts.Password;
import org.taonaw.identityaccess.domain.shared.exception.DomainException;
import org.taonaw.identityaccess.domain.shared.exception.DomainExceptionCodes;

@Service
@AllArgsConstructor
public class LoginAccountAppService {

    @Autowired
    private final IAccountRepository accountRepository;

    public LoginAccountResponse handle(LoginAccountRequest request) {

        var account = accountRepository.find(new LoginId(request.getLoginId()));
        if (account.isEmpty()) {
            throw new DomainException(DomainExceptionCodes.LoginAccountNotFound);
        }

        if (!account.get().authenticate(Password.of(request.getPassword()))) {
            throw new DomainException(DomainExceptionCodes.LoginAccountPasswordNotMatched);
        }

        return LoginAccountResponse.builder()
                .loginId(request.getLoginId())
                .build();
    }
}
