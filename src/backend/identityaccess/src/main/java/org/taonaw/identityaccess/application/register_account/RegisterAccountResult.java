package org.taonaw.identityaccess.application.register_account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterAccountResult {
    @NonNull private String accountId;
}
