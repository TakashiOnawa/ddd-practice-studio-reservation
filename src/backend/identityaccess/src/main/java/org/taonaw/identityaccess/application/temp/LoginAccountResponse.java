package org.taonaw.identityaccess.application.temp;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class LoginAccountResponse {
    @NonNull
    private String loginId;
}
