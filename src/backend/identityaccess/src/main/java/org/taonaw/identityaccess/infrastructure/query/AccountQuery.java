package org.taonaw.identityaccess.infrastructure.query;

import org.springframework.stereotype.Repository;
import org.taonaw.identityaccess.query.IAccountQuery;
import org.taonaw.identityaccess.query.dto.AccountQueryDto;

import java.util.Optional;

@Repository
public class AccountQuery implements IAccountQuery {

    public Optional<AccountQueryDto> accountByName(String accountName) {
        return Optional.empty();
    }
}
