package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PlainTextPassword {
    private final String value;

    public PlainTextPassword(String value) {
        this.value = value;
    }

    public boolean matches(HashedPassword hashedPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(value, hashedPassword.getValue());
    }
}
