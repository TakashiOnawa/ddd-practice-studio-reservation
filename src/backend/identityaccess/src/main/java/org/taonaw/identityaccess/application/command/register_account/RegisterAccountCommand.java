package org.taonaw.identityaccess.application.command.register_account;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class RegisterAccountCommand {
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String loginId;
    @NonNull private String password;
}
