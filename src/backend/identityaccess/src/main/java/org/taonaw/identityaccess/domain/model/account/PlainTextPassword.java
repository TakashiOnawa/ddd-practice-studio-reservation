package org.taonaw.identityaccess.domain.model.account;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@EqualsAndHashCode
public class PlainTextPassword {
    private final String value;

    public PlainTextPassword(@NonNull String value) {
        Assertion.argumentRange(value, 8, 20);
        Assertion.argumentPatternMatches(value, "[a-zA-Z0-9]*", "半角英数でなければなりません。");
        this.value = value;
    }

    public Password encode(@NonNull IPasswordEncoder passwordEncoder) {
        return new Password(passwordEncoder.encode(value));
    }

    public boolean matches(@NonNull Password password, @NonNull IPasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(value, password.getValue());
    }
}
