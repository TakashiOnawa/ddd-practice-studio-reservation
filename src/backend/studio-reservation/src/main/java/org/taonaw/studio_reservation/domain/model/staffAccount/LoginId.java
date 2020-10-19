package org.taonaw.studio_reservation.domain.model.staffAccount;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class LoginId {
    private final String value;

    public LoginId(String value) {
        Assertion.argumentRange(value, 1, 16);
        Assertion.argumentPattern(value, "[a-zA-Z0-9]*", "半角英数でなければなりません。");
        this.value = value;
    }
}
