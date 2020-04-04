package org.taonaw.identityaccess.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.common.DeepCopy;
import org.taonaw.identityaccess.domain.model.account.Account;
import org.taonaw.identityaccess.domain.model.account.IAccountRepository;
import org.taonaw.identityaccess.domain.model.account.LoginId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepository implements IAccountRepository {

    private final static List<Account> accounts = new ArrayList<>();

    public List<Account> findAll() {
        return accounts.stream()
                .map(item -> DeepCopy.clone(item, Account.class))
                .collect(Collectors.toList());
    }

    public Optional<Account> find(LoginId loginId) {
        var account = accounts.stream()
                .filter(item -> item.loginId().equals(loginId))
                .findFirst();

        if (account.isEmpty())
            return account;

        return Optional.of(DeepCopy.clone(account.get(), Account.class));
    }

    public void save(@NonNull Account account) {
        accounts.add(DeepCopy.clone(account, Account.class));
    }
}
