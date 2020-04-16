package org.taonaw.reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.member.MemberId;
import org.taonaw.reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UserInformation {
    private final MemberId memberId;
    private final String name;
    private final String phoneNumber;

    public UserInformation(@NonNull String name, @NonNull String phoneNumber) {
        this(MemberId.NON_MEMBER_ID, name, phoneNumber);
    }

    public UserInformation(@NonNull MemberId memberId, @NonNull String name, @NonNull String phoneNumber) {
        Assertion.argumentRange(name, 1, 50);
        Assertion.argumentRange(phoneNumber, 5, 20);
        Assertion.argumentPatternMatches(phoneNumber, "[0-9]*", "数字でなければなりません。");
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public boolean isMembersInformation() {
        return !memberId.isNonMember();
    }
}
