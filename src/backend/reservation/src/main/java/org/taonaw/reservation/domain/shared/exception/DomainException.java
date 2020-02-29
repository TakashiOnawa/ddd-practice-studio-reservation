package org.taonaw.reservation.domain.shared.exception;

import lombok.NonNull;

import java.util.Arrays;

public class DomainException extends RuntimeException {
    private final DomainExceptionCodes code;

    public DomainException(@NonNull DomainExceptionCodes code) {
        this(code, code.getDefaultMessage());
    }

    public DomainException(@NonNull DomainExceptionCodes code, String message) {
        super(code.getDefaultMessage());
        this.code = code;
    }

    public boolean in(@NonNull DomainExceptionCodes... codes) {
        return Arrays.asList(codes).contains(this.code);
    }
}
