package org.taonaw.identityaccess.application.loginaccount;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class LoginAccountResponse {
    @NonNull
    private String loginId;
}
