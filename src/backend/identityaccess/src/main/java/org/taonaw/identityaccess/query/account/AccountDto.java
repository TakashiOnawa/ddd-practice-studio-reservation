package org.taonaw.identityaccess.query.account;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class AccountDto {
    @NonNull private String accountId;
    @NonNull private String accountFirstName;
    @NonNull private String accountLastName;
    @NonNull private String accountFullName;
    @NonNull private String loginId;
}
