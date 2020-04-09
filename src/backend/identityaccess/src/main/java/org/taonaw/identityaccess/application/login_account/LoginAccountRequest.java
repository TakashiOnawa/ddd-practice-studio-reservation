package org.taonaw.identityaccess.application.login_account;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class LoginAccountRequest {
    @NonNull private String loginId;
    @NonNull private String password;
}
