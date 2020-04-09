package org.taonaw.identityaccess.domain.model.account;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {
    List<Account> findAll();
    Optional<Account> findBy(@NonNull LoginId loginId);
    void add(@NonNull Account account);
}
