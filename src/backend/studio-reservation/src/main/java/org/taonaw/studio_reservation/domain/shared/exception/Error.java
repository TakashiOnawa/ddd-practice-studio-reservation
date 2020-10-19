package org.taonaw.studio_reservation.domain.shared.exception;

import lombok.Getter;

@Getter
public class Error {
    private final String message;

    public Error(String message) {
        this.message = message;
    }

    public void throwError() {
        var errorNotification = new ErrorNotification();
        errorNotification.addError(this);
        errorNotification.throwIfHasErrors(message);
    }
}
