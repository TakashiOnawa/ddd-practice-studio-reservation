package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.shared.PersonName;
import org.taonaw.studio_reservation.domain.model.shared.PhoneNumber;
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

    public UserInformation(PersonName personName, PhoneNumber phoneNumber) {
        Assertion.required(personName);
        Assertion.required(phoneNumber);
        this.userName = personName.asFormattedName();
        this.userPhoneNumber = phoneNumber.asFormattedNumber();
    }
}
