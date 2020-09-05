package org.taonaw.studio_reservation.domain.model.shared;

public interface PasswordEncoder {
    boolean matches(String plainTextPassword, String hashedPassword);
}
