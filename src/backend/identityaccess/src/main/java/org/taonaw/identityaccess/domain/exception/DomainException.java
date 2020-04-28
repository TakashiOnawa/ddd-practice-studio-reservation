package org.taonaw.identityaccess.domain.exception;

import lombok.NonNull;

public class DomainException extends RuntimeException {
    public DomainException(@NonNull String message) {
        super(message);
    }

    public DomainException(@NonNull String message, @NonNull Throwable cause) {
        super(message, cause);
    }
}
