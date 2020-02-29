package org.taonaw.managementsite.application.identityaccess.getaccounts;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountsResponse {
    @NonNull
    private List<AccountDto> accounts;
}
