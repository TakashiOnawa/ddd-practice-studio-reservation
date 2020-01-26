package org.taonaw.identityaccess.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.domain.model.accounts.Account;
import org.taonaw.identityaccess.domain.model.accounts.IAccountRepository;

import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository {
    public List<Account> findAll() {
        throw new IllegalCallerException();
    }

    public void save(@NonNull Account account) {
        throw new IllegalCallerException();
    }
}
