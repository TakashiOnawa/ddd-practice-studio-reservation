package org.taonaw.identityaccess.infrastructure.query;

import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.query.IAccountQuery;
import org.taonaw.identityaccess.query.dto.AccountQueryDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountQuery implements IAccountQuery {

    public Optional<AccountQueryDto> findByLoginId(String loginId) {

        return findAll().stream().filter(item -> item.getLoginId().equals(loginId)).findFirst();
    }

    public List<AccountQueryDto> findAll() {

        var accounts = new ArrayList<AccountQueryDto>();

        for (var i = 0;  i < 30; i++) {
            var account = AccountQueryDto.builder()
                    .accountId(String.valueOf(i + 1))
                    .loginId(String.valueOf(i + 1))
                    .password("$2a$10$vZzdISmDQqPYGHwYrrqvB.ijiOPhyy/ZhLAEUJdXnZ86jJIvNZsgS")
                    .accountName("テストアカウント" + String.valueOf(i + 1))
                    .build();
            accounts.add(account);
        }

        return accounts;
    }
}
