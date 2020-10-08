package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class PersonName {
    private final String firstName;
    private final String lastName;

    public PersonName(String firstName, String lastName) {
        Assertion.argumentRange(firstName, 1, 50);
        Assertion.argumentRange(lastName, 1, 50);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String asFormattedName() {
        return lastName + " " + firstName;
    }
}
