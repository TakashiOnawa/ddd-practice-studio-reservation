package org.taonaw.identityaccess.infrastructure.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.taonaw.identityaccess.domain.model.shared.IPasswordEncoder;

@Service
@AllArgsConstructor
public class BCryptPasswordEncoder implements IPasswordEncoder {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public String encode(@NonNull String plainTextPassword) {
        return passwordEncoder.encode(plainTextPassword);
    }

    public boolean matches(@NonNull String plainTextPassword, @NonNull String encodedPassword) {
        return passwordEncoder.matches(plainTextPassword, encodedPassword);
    }
}
