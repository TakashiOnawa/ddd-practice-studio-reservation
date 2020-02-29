package org.taonaw.identityaccess.query.account;

import java.util.Optional;

public interface IAccountQuery {
    GetAccountsResponse getAccounts();
    Optional<AccountDto> getAccountById(String accountId);
}
