package org.taonaw.identityaccess.query.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetAccountsResponse {
    @NonNull
    private List<AccountDto> accounts;
}
