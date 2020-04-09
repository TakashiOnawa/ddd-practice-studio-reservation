package org.taonaw.identityaccess.domain.model.shared;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@Getter(value = AccessLevel.PACKAGE)
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
}
