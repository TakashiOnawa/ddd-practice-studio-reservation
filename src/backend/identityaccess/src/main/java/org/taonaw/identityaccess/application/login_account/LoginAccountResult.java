package org.taonaw.identityaccess.application.login_account;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class LoginAccountResult {
    @NonNull private String loginId;
}
