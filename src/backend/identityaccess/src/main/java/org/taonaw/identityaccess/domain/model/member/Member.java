package org.taonaw.identityaccess.domain.model.member;

import lombok.NonNull;
import org.taonaw.identityaccess.domain.model.shared.EmailAddress;
import org.taonaw.identityaccess.domain.model.shared.IPasswordEncoder;
import org.taonaw.identityaccess.domain.model.shared.Password;
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword;

import java.util.Objects;

public class Member {
    private MemberId memberId;
    private MemberDetail detail;
    private Password password;

    public static Member newMember(@NonNull MemberDetail detail,
                                   @NonNull Password password) {
        var member = new Member();
        member.memberId = MemberId.newId();
        member.detail = detail;
        member.password = password;
        return member;
    }

    public static Member reconstruct(@NonNull MemberId memberId,
                                     @NonNull MemberDetail detail,
                                     @NonNull Password password) {
        var member = new Member();
        member.memberId = memberId;
        member.detail = detail;
        member.password = password;
        return member;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public MemberDetail getDetail() {
        return detail;
    }

    public Password getPassword() {
        return password;
    }

    public boolean authenticate(@NonNull EmailAddress emailAddress,
                                @NonNull PlainTextPassword plainTextPassword,
                                @NonNull IPasswordEncoder passwordEncoder) {
        return  detail.getEmailAddress().equals(emailAddress) && password.matches(plainTextPassword, passwordEncoder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
