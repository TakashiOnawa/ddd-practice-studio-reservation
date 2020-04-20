package org.taonaw.identityaccess.application.login_member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.member.Member;

@Getter
@Builder
@AllArgsConstructor
public class LoginMemberResult {
    @NonNull private String emailAddress;

    static LoginMemberResult of(Member member) {
        return builder()
                .emailAddress(member.getDetail().getEmailAddress().getValue())
                .build();
    }
}
