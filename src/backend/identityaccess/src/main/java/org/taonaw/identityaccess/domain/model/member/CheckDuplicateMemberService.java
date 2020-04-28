package org.taonaw.identityaccess.domain.model.member;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.exception.MemberDuplicatedException;

@AllArgsConstructor
public class CheckDuplicateMemberService {
    @NonNull
    private final IMemberRepository memberRepository;

    public boolean isDuplicated(@NonNull Member member) {
        return memberRepository.findBy(member.getDetail().getEmailAddress()).isPresent();
    }

    public void validate(Member member) {
        if (isDuplicated(member)) {
            throw new MemberDuplicatedException(member.getDetail().getEmailAddress());
        }
    }
}
