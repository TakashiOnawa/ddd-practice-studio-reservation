package org.taonaw.facilitymanagement.domain.shared.exception;

import lombok.NonNull;

import java.util.Arrays;

public class DomainException extends RuntimeException {
    private final DomainExceptionCodes code;

    public DomainException(@NonNull DomainExceptionCodes code, String message) {
        super(message);
        this.code = code;
    }

    public DomainException(@NonNull DomainExceptionCodes code) {
        this(code, code.getDefaultMessage());
    }

    public DomainException(@NonNull String message) {
        this(DomainExceptionCodes.InvalidOperation, message);
    }

    public boolean in(@NonNull DomainExceptionCodes... codes) {
        return Arrays.asList(codes).contains(this.code);
    }
}
