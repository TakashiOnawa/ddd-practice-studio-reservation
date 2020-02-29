package org.taonaw.managementsite.application.identityaccess.loginaccount;

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
