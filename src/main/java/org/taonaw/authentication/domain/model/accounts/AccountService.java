package org.taonaw.authentication.domain.model.accounts;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountService {

    @NonNull
    @Autowired
    private final IAccountRepository accountRepository;

    public boolean sameEmailAddressAccountRegistered(@NonNull Account account) {
        var allAccounts = accountRepository.findAll();
        return allAccounts.stream().anyMatch(account::isEmailAddressEquals);
    }
}
