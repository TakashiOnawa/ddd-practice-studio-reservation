package org.taonaw.managementsite.application.identityaccess.query;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @NonNull private String accountId;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String fullName;
    @NonNull private String loginId;
}
