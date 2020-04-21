package org.taonaw.identityaccess.application.command.register_member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.member.Member;

@Getter
@Builder
@AllArgsConstructor
public class RegisterMemberResult {
    @NonNull private final String memberId;

    static RegisterMemberResult of(Member member) {
        return builder()
                .memberId(member.getMemberId().getValue())
                .build();
    }
}
