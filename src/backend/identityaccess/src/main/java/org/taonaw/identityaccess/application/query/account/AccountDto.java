package org.taonaw.identityaccess.application.query.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class AccountDto {
    @NonNull private String accountId;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String fullName;
    @NonNull private String loginId;
}
