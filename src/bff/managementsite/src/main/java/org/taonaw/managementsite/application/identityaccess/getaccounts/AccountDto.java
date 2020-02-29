package org.taonaw.managementsite.application.identityaccess.getaccounts;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @NonNull
    private String accountId;
    @NonNull
    private String accountFirstName;
    @NonNull
    private String accountLastName;
    @NonNull
    private String accountFullName;
    @NonNull
    private String loginId;
}
