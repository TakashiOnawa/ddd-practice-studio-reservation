package org.taonaw.authentication.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.authentication.application.commands.RegisterAccountRequest;
import org.taonaw.authentication.domain.model.accounts.*;
import org.taonaw.authentication.domain.model.roles.RoleId;

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
                new RoleId(request.getRoleId()),
                new FullName(request.getFirstName(), request.getLastName()),
                new DateOfBirth(request.getDateOfBirth()),
                new EmailAddress(request.getEmailAddress()),
                new Password(request.getPassword()));

        if (accountService.sameEmailAddressAccountRegistered(account)) {
        }

        accountRepository.save(account);
    }
}
