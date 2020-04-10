package org.taonaw.reservation.domain.model.member;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UserInformation;

import java.util.Objects;

public class Member {
    private final MemberId memberId;
    private final MemberName name;
    private final MemberPhoneNumber phoneNumber;

    public Member(
            @NonNull MemberId memberId,
            @NonNull MemberName name,
            @NonNull MemberPhoneNumber phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public UserInformation createUserInformation() {
        return new UserInformation(memberId, name.asFormattedName(), phoneNumber.asFormattedPhoneNumber());
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
