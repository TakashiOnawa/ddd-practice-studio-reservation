package org.taonaw.identityaccess.domain.model.accounts;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {
    List<Account> findAll();
    Optional<Account> find(@NonNull LoginId loginId);
    void save(@NonNull Account account);
}
