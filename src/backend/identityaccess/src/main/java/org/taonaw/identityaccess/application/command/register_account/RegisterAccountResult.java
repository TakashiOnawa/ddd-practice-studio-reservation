package org.taonaw.identityaccess.application.command.register_account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.account.Account;

@Getter
@Builder
@AllArgsConstructor
public class RegisterAccountResult {
    @NonNull private String accountId;

    static RegisterAccountResult of(Account account) {
        return builder()
                .accountId(account.getAccountId().getValue())
                .build();
    }
}
