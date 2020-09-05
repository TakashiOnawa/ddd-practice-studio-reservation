package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class PlainTextPassword {
    private final String value;

    public PlainTextPassword(String value) {
        Assertion.argumentRange(value, 8, 20);
        Assertion.argumentPattern(value, "[a-zA-Z0-9]*", "半角英数でなければなりません。");
        this.value = value;
    }

    public boolean matches(@NonNull HashedPassword hashedPassword, @NonNull PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(value, hashedPassword.getValue());
    }

    public HashedPassword createHashedPassword(@NonNull PasswordEncoder passwordEncoder) {
        return new HashedPassword(passwordEncoder.encode(value));
    }
}
