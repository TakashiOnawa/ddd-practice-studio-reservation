package org.taonaw.identityaccess.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class PhoneNumber {
    private final String areaCode;
    private final String localNumber;
    private final String subscriberNumber;

    public PhoneNumber(@NonNull String areaCode,
                       @NonNull String localNumber,
                       @NonNull String subscriberNumber) {
        Assertion.argumentPatternMatches(areaCode, "", "数字でなければなりません。");
        Assertion.argumentPatternMatches(localNumber, "", "数字でなければなりません。");
        Assertion.argumentPatternMatches(subscriberNumber, "", "数字でなければなりません。");
        this.areaCode = areaCode;
        this.localNumber = localNumber;
        this.subscriberNumber = subscriberNumber;
    }

    public String asFormattedPhoneNumber() {
        return areaCode + "-" + localNumber + "-" + subscriberNumber;
    }
}
