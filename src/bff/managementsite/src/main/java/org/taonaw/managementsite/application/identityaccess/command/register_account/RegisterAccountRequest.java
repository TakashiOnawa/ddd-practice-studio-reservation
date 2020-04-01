package org.taonaw.managementsite.application.identityaccess.command.register_account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterAccountRequest {
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String loginId;
    @NonNull private String password;
}
