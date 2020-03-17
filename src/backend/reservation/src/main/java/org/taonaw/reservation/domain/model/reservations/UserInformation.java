package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.members.Member;
import org.taonaw.reservation.domain.model.members.MemberId;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class UserInformation {

    private final String name;
    private final String phoneNumber;
    private MemberId memberId = MemberId.nonMember();

    public UserInformation(@NonNull String name, @NonNull String phoneNumber) {
        Assertion.argumentRange(name, 1, 50);
        Assertion.argumentRange(phoneNumber, 5, 20);
        Assertion.argumentPatternMatches(phoneNumber, "[0-9]*", "数字でなければなりません。");
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static UserInformation of(Member member) {
        var userInformation = new UserInformation(member.name(), member.phoneNumber());
        userInformation.memberId = member.memberId();
        return userInformation;
    }
}
