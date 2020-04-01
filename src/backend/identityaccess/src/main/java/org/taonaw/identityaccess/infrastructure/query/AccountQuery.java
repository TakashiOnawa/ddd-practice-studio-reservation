package org.taonaw.identityaccess.infrastructure.query;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.domain.model.account.IAccountRepository;
import org.taonaw.identityaccess.query.account.AccountDto;
import org.taonaw.identityaccess.query.account.IAccountQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountQuery implements IAccountQuery {

    @Autowired
    private final IAccountRepository accountRepository;

    public List<AccountDto> getAccounts() {
        var accounts = new ArrayList<AccountDto>();
        for (var account : accountRepository.findAll()) {
            var accountDto = AccountDto.builder()
                    .accountId(account.accountId().getValue())
                    .accountFirstName(account.accountName().getFirstName())
                    .accountLastName(account.accountName().getLastName())
                    .accountFullName(account.accountName().getFullName())
                    .loginId(account.loginId().getValue())
                    .build();
            accounts.add(accountDto);
        }
        return accounts;
    }

    public List<AccountDto> findAll() {
        var accounts = new ArrayList<AccountDto>();
        for (var account : accountRepository.findAll()) {
            var accountDto = AccountDto.builder()
                    .accountId(account.accountId().getValue())
                    .accountFirstName(account.accountName().getFirstName())
                    .accountLastName(account.accountName().getLastName())
                    .accountFullName(account.accountName().getFullName())
                    .loginId(account.loginId().getValue())
                    .build();
            accounts.add(accountDto);
        }
        return accounts;
    }

    public Optional<AccountDto> getAccountById(String accountId) {
        return findAll().stream()
                .filter(item -> item.getAccountId().equals(accountId))
                .findFirst();
    }
}
