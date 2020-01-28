package org.taonaw.identityaccess.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class AccountQueryDto {

    @NonNull
    private String accountName;
    @NonNull
    private String password;
}
