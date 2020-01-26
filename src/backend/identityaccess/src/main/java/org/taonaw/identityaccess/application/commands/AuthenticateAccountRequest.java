package org.taonaw.identityaccess.application.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class AuthenticateAccountRequest {
    @NonNull
    private String accountName;
    @NonNull
    private String password;
}
