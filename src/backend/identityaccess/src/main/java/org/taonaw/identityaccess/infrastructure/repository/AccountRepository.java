package org.taonaw.identityaccess.infrastructure.repository;

import com.google.gson.Gson;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.domain.model.account.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepository implements IAccountRepository {

    private final static List<Account> accounts = new ArrayList<>();

    public List<Account> findAll() {
        return accounts.stream()
                .map(this::deepCopy)
                .collect(Collectors.toList());
    }

    public Optional<Account> find(LoginId loginId) {
        var account = accounts.stream()
                .filter(item -> item.loginId().equals(loginId))
                .findFirst();

        if (account.isEmpty())
            return account;

        return Optional.of(deepCopy(account.get()));
    }

    public void save(@NonNull Account account) {
        accounts.add(deepCopy(account));
    }

    private Account deepCopy(Account account) {
        var gson = new Gson();
        return gson.fromJson(gson.toJson(account), Account.class);
    }
}
