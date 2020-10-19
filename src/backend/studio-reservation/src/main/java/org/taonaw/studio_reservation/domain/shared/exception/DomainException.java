package org.taonaw.studio_reservation.domain.shared.exception;

import lombok.NonNull;

import java.util.Arrays;

public class DomainException extends RuntimeException {
    private final ErrorNotification errorNotification;

    public DomainException(String message) {
        super(message);
        errorNotification = new ErrorNotification();
    }

    public DomainException(String message, @NonNull ErrorNotification errorNotification) {
        super(message);
        this.errorNotification = errorNotification;
    }

    public DomainException(String message, @NonNull ErrorNotification... errorNotifications) {
        super(message);
        errorNotification = new ErrorNotification();
        Arrays.stream(errorNotifications).forEach(errorNotification::addError);
    }

    public DomainException(@NonNull Error error) {
        super(error.getMessage());
        this.errorNotification = new ErrorNotification();
        this.errorNotification.addError(error);
    }

    public void throwIfHasErrors() {
        if (errorNotification.hasErrors())
            throw this;
    }

    @Override
    public String toString() {
        return super.toString() + "{" + "errorNotification=" + errorNotification.errorMessage() + "}";
    }
}
