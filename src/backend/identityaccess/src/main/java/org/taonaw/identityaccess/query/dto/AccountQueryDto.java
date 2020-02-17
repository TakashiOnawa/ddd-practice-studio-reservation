package org.taonaw.identityaccess.query.dto;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
public class AccountQueryDto {

    @NonNull
    private String accountId;
    @NonNull
    private String loginId;
    @NonNull
    private String password;
    @NonNull
    private String accountName;
}
