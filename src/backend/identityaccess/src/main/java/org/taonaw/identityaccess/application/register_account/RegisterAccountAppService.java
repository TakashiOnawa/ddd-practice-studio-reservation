package org.taonaw.identityaccess.application.register_account;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.taonaw.identityaccess.domain.model.account.*;
import org.taonaw.identityaccess.domain.shared.exception.DomainException;
import org.taonaw.identityaccess.domain.shared.exception.DomainExceptionCodes;

@Service
@AllArgsConstructor
public class RegisterAccountAppService {

    @Autowired
    private final IAccountRepository accountRepository;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final IPasswordEncoder passwordEncoder;

//    @Transactional
    public RegisterAccountResponse handle(RegisterAccountRequest request) {

        var account = Account.newAccount(
                new AccountName(request.getFirstName(), request.getLastName()),
                new LoginId(request.getLoginId()),
                new PlainTextPassword(request.getPassword()).encode(passwordEncoder));

        if (accountService.isDuplicated(account)) {
            throw new DomainException(DomainExceptionCodes.AccountDuplicated);
        }

        accountRepository.save(account);

        return RegisterAccountResponse.builder()
                .accountId(account.accountId().getValue())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .loginId(request.getLoginId())
                .build();
    }
}
