package org.taonaw.identityaccess.domain.model.accounts;

import lombok.NonNull;

public interface IPasswordEncoder {
    String encode(@NonNull String plainTextPassword);
    boolean matches(@NonNull String plainTextPassword, @NonNull String encodedPassword);
}
