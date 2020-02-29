package org.taonaw.identityaccess.domain.model.accounts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class Password {
    private final String value;

    private Password(@NonNull String value) {
        this.value = value;
    }

    public static Password of(@NonNull String plainTextPassword) {
        Assertion.argumentRange(plainTextPassword, 8, 20);
        Assertion.argumentPatternMatches(plainTextPassword, "[a-zA-Z0-9]*", "半角英数でなければなりません。");
        // TODO:ハッシュ化して保持する。
        var hashedPassword = plainTextPassword;
        return new Password(hashedPassword);
    }

    public static Password reconstruct(@NonNull String value) {
        return new Password(value);
    }
}
