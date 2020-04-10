package org.taonaw.identityaccess.query.account;

import java.util.List;
import java.util.Optional;

public interface IAccountQuery {
    List<AccountDto> getAll();
    Optional<AccountDto> getByAccountId(String accountId);
}
