package org.taonaw.identityaccess.application.loginaccount;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class LoginAccountRequest {
    @NonNull
    private String loginId;
    @NonNull
    private String password;
}
