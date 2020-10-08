package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class PhoneNumber {
    private final String areaCode;
    private final String localNumber;
    private final String subscriberNumber;

    public PhoneNumber(@NonNull String areaCode,
                       @NonNull String localNumber,
                       @NonNull String subscriberNumber) {

        Assertion.argumentPattern(areaCode, "[0-9]*", "市外局番は半角数字でなければなりません。");
        Assertion.argumentRange(areaCode, 2, 4);
        Assertion.argumentPattern(localNumber, "[0-9]*", "市内局番半角数字でなければなりません。");
        Assertion.argumentRange(localNumber, 2, 4);
        Assertion.argumentPattern(subscriberNumber, "[0-9]*", "加入者番号半角数字でなければなりません。");
        Assertion.argumentRange(subscriberNumber, 2, 4);
        this.areaCode = areaCode;
        this.localNumber = localNumber;
        this.subscriberNumber = subscriberNumber;
    }

    public String asFormattedNumber() {
        return areaCode + "-" + localNumber + "-" + subscriberNumber;
    }
}
