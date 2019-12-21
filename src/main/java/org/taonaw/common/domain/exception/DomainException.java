package org.taonaw.common.domain.exception;

public class DomainException extends  RuntimeException {
    public DomainException(DomainExceptionCodes code) {
        this(code, code.getDefaultMessage());
    }
    public DomainException(DomainExceptionCodes code, String message) {
        super(code.getDefaultMessage());
    }
}
