package org.taonaw.identityaccess.query;

import org.taonaw.identityaccess.query.dto.AccountQueryDto;

import java.util.List;
import java.util.Optional;

public interface IAccountQuery {
    Optional<AccountQueryDto> findByLoginId(String loginId);
    List<AccountQueryDto> findAll();
}
