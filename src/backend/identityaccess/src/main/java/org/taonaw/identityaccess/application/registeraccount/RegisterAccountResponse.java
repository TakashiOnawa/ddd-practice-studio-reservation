package org.taonaw.identityaccess.application.registeraccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterAccountResponse {
    @NonNull
    private String accountId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String loginId;
}
