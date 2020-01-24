package org.taonaw.identityaccess.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.application.commands.RegisterAccountRequest;
import org.taonaw.identityaccess.domain.model.accounts.*;

@Service
@AllArgsConstructor
public class RegisterAccountAppService {

    @NonNull
    @Autowired
    private final IAccountRepository accountRepository;
    @NonNull
    @Autowired
    private final AccountService accountService;

    public void registerAccount(RegisterAccountRequest request) {

        var account = Account.newAccount(
                new AccountName(request.getAccountName()),
                new FullName(request.getFirstName(), request.getLastName()),
                new Password(request.getPassword()));

        account.assignRole(request.getRoleIds());

        if (accountService.sameAccountNameRegistered(account)) {
            // TODO:業務例外
        }

        accountRepository.save(account);
    }
}