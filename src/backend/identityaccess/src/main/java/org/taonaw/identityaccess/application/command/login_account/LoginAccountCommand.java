package org.taonaw.identityaccess.application.command.login_account;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class LoginAccountCommand {
    @NonNull private String loginId;
    @NonNull private String password;
}
