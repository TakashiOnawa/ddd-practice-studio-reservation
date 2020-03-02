package org.taonaw.identityaccess.domain.model.accounts;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Password {
    private final String value;
}
