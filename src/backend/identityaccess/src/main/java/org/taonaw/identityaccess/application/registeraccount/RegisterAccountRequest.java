package org.taonaw.identityaccess.application.registeraccount;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class RegisterAccountRequest {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String loginId;
    @NonNull
    private String password;
}
