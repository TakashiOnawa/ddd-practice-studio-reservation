package org.taonaw.identityaccess.domain.model.account;

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

    public boolean isDuplicated(@NonNull Account account) {
        var duplicateAccount = accountRepository.find(account.loginId());
        return duplicateAccount.isPresent();
    }
}
