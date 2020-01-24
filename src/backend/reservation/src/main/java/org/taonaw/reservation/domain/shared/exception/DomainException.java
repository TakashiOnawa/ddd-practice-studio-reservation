package org.taonaw.reservation.domain.shared.exception;

import lombok.NonNull;

public class DomainException extends  RuntimeException {
    public DomainException(@NonNull DomainExceptionCodes code) {
        this(code, code.getDefaultMessage());
    }
    public DomainException(@NonNull DomainExceptionCodes code, String message) {
        super(code.getDefaultMessage());
    }
}
