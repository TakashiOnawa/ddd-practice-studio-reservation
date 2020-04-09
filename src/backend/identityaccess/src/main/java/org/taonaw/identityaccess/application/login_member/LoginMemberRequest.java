package org.taonaw.identityaccess.application.login_member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class LoginMemberRequest {
    @NonNull private String emailAddress;
    @NonNull private String password;
}
