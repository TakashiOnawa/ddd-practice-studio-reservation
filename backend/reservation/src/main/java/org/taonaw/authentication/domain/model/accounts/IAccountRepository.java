package org.taonaw.authentication.domain.model.accounts;

import lombok.NonNull;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    void save(@NonNull Account account);
}
