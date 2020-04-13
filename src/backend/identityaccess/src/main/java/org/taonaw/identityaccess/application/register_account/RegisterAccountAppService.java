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
    public RegisterAccountResult handle(RegisterAccountCommand command) {

        var account = Account.newAccount(
                new FullName(command.getFirstName(), command.getLastName()),
                new LoginId(command.getLoginId()),
                new PlainTextPassword(command.getPassword()).encode(passwordEncoder));

        new CheckDuplicateAccountService(accountRepository).validate(account);

        accountRepository.add(account);

        return RegisterAccountResult.builder()
                .accountId(account.getAccountId().getValue())
                .build();
    }
}
