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

    @Override
    public List<AccountDto> getAll() {
        var accounts = new ArrayList<AccountDto>();
        for (var account : accountRepository.findAll()) {
            var accountDto = AccountDto.builder()
                    .accountId(account.getAccountId().getValue())
                    .firstName(account.getName().getFirstName())
                    .lastName(account.getName().getLastName())
                    .fullName(account.getName().asFormattedName())
                    .loginId(account.getLoginId().getValue())
                    .build();
            accounts.add(accountDto);
        }
        return accounts;
    }

    @Override
    public Optional<AccountDto> getByAccountId(String accountId) {
        return getAll().stream()
                .filter(item -> item.getAccountId().equals(accountId))
                .findFirst();
    }
}
