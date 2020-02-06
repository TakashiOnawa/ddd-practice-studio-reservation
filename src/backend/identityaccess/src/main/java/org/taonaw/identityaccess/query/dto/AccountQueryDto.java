package org.taonaw.identityaccess.query.dto;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
public class AccountQueryDto {

    @NonNull
    private String accountId;
    @NonNull
    private String accountName;
    @NonNull
    private String password;
}
