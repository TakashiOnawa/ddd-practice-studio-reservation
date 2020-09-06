package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UserInformation {
    private final String userName;
    private final String userPhoneNumber;

    public UserInformation(String userName, String userPhoneNumber) {
        Assertion.argumentRange(userName, 1, 101);
        Assertion.argumentRange(userPhoneNumber, 6, 14);
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }
}
