package org.taonaw.identityaccess.query;

import org.taonaw.identityaccess.query.dto.AccountQueryDto;

import java.util.Optional;

public interface IAccountQuery {
    Optional<AccountQueryDto> accountByName(String accountName);
}
