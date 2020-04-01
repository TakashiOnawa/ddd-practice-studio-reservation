package org.taonaw.identityaccess.domain.model.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class LoginId {
    private final String value;

    public LoginId(@NonNull String value) {
        Assertion.argumentRange(value, 1, 16);
        Assertion.argumentPatternMatches(value, "[a-zA-Z0-9]*", "半角英数でなければなりません。");
        this.value = value;
    }
}
