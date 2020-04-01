package org.taonaw.managementsite.application.identityaccess.command.login_account;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginAccountResponse {
    @NonNull private String loginId;
}

