package org.taonaw.reservation.domain.model.member;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class MemberName {
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;

    public String asFormattedName() {
        return lastName + " " + firstName;
    }
}
