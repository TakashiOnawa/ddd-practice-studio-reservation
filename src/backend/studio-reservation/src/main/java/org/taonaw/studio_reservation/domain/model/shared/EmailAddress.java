package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EmailAddress {
    private final String value;

    public EmailAddress(String value) {
        Assertion.argumentRange(value, 1, 100);
        Assertion.argumentPattern(value,"\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", "フォーマットが不正です。");
        this.value = value;
    }
}
