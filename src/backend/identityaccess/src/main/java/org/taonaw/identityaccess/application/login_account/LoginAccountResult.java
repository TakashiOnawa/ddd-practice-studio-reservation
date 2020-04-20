package org.taonaw.identityaccess.application.login_account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.account.Account;

@Getter
@Builder
@AllArgsConstructor
public class LoginAccountResult {
    @NonNull private String loginId;

    static LoginAccountResult of(Account account) {
        return builder()
                .loginId(account.getLoginId().getValue())
                .build();
    }
}
