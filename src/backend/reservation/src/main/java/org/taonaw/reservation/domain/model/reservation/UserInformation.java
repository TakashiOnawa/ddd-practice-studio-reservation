package org.taonaw.reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class UserInformation {
    private final String name;
    private final String phoneNumber;

    public UserInformation(@NonNull String name, @NonNull String phoneNumber) {
        Assertion.argumentRange(name, 1, 50);
        Assertion.argumentRange(phoneNumber, 5, 20);
        Assertion.argumentPatternMatches(phoneNumber, "[0-9]*", "数字でなければなりません。");
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
