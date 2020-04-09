package org.taonaw.identityaccess.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EmailAddress {
    private final String value;

    public EmailAddress(@NonNull String value) {
        Assertion.argumentRange(value, 1, 100);
        Assertion.argumentPatternMatches(value,
                "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",
                "Email アドレスのフォーマットが不正です。");
        this.value = value;
    }
}
