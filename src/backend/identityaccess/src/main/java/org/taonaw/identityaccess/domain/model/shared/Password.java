package org.taonaw.identityaccess.domain.model.shared;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Password {
    private final String value;

    public boolean matches(@NonNull PlainTextPassword plainTextPassword, @NonNull IPasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainTextPassword.getValue(), value);
    }
}
