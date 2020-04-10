package org.taonaw.reservation.domain.model.member;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class MemberPhoneNumber {
    @NonNull
    private final String areaCode;
    @NonNull
    private final String localNumber;
    @NonNull
    private final String subscriberNumber;

    public String asFormattedPhoneNumber() {
        return areaCode + "-" + localNumber + "-" + subscriberNumber;
    }
}
