package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
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

    public UserInformation(@NonNull PersonName personName, @NonNull PhoneNumber phoneNumber) {
        this(personName.asFormattedName(), phoneNumber.asFormattedNumber());
    }
}
