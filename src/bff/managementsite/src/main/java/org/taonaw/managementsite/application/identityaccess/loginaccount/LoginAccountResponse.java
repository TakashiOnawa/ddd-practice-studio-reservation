package org.taonaw.managementsite.application.identityaccess.loginaccount;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginAccountResponse {
    @NonNull
    private String loginId;
}

