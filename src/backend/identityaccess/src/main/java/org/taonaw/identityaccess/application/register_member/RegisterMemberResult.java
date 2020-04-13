package org.taonaw.identityaccess.application.register_member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterMemberResult {
    @NonNull private final String memberId;
}
