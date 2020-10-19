package org.taonaw.studio_reservation.domain.model.shared;

public interface PasswordEncoder {
    String encode(String plainTextPassword);
    boolean matches(String plainTextPassword, String hashedPassword);
}
