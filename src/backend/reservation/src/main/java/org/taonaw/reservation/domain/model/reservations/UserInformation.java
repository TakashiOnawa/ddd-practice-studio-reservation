package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class UserInformation {

    private static final UserInformation none = new UserInformation("None", "00000");

    private final String name;
    private final String phoneNumber;

    public UserInformation(@NonNull String name, @NonNull String phoneNumber) {
        Assertion.argumentRange(name, 1, 50);
        Assertion.argumentRange(phoneNumber, 5, 20);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static UserInformation none() {
        return none;
    }
}
