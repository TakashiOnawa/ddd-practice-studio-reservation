package org.taonaw.identityaccess.application.register_account;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.model.account.Account;
import org.taonaw.identityaccess.domain.model.account.CheckDuplicateAccountService;
import org.taonaw.identityaccess.domain.model.account.IAccountRepository;
import org.taonaw.identityaccess.domain.model.account.LoginId;
import org.taonaw.identityaccess.domain.model.shared.FullName;
import org.taonaw.identityaccess.domain.model.shared.IPasswordEncoder;
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword;

//import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegisterAccountAppService {
    @Autowired
    private final IAccountRepository accountRepository;
    @Autowired
    private final IPasswordEncoder passwordEncoder;

//    @Transactional
    public RegisterAccountResponse handle(RegisterAccountRequest request) {

        var account = Account.newAccount(
                new FullName(request.getFirstName(), request.getLastName()),
                new LoginId(request.getLoginId()),
                new PlainTextPassword(request.getPassword()).encode(passwordEncoder));

        new CheckDuplicateAccountService(accountRepository).validate(account);

        accountRepository.add(account);

        return RegisterAccountResponse.builder()
                .accountId(account.getAccountId().getValue())
                .build();
    }
}
