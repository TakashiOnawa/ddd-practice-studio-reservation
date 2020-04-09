package org.taonaw.identityaccess.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.common.DeepCopy;
import org.taonaw.identityaccess.domain.model.account.Account;
import org.taonaw.identityaccess.domain.model.account.AccountId;
import org.taonaw.identityaccess.domain.model.account.IAccountRepository;
import org.taonaw.identityaccess.domain.model.account.LoginId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepository implements IAccountRepository {
    private final static Map<AccountId, Account> values = new HashMap<>();

    public List<Account> findAll() {
        return values.values().stream()
                .map(item -> DeepCopy.clone(item, Account.class))
                .collect(Collectors.toList());
    }

    public Optional<Account> findBy(LoginId loginId) {
        var account = values.values().stream()
                .filter(item -> item.getLoginId().equals(loginId))
                .findFirst();
        if (account.isEmpty()) {
            return account;
        }
        return Optional.of(DeepCopy.clone(account.get(), Account.class));
    }

    public void add(@NonNull Account account) {
        values.put(account.getAccountId(), DeepCopy.clone(account, Account.class));
    }
}
