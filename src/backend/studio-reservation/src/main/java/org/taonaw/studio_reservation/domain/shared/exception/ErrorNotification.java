package org.taonaw.studio_reservation.domain.shared.exception;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ErrorNotification {
    private final List<Error> errors = new ArrayList<>();

    public void addError(String message) {
        if (message == null || message.isEmpty())
            return;

        errors.add(new Error(message));
    }

    public void addError(Error error) {
        if (error == null)
            return;

        errors.add(error);
    }

    public void addError(Optional<Error> error) {
        if (error.isEmpty())
            return;

        errors.add(error.get());
    }

    public void addError(ErrorNotification errorNotification) {
        if (errorNotification == null)
            return;

        errors.addAll(errorNotification.errors);
    }

    public <T extends Error> Optional<T> getFirstError(@NonNull Class<T> type) {
        return errors.stream()
                .filter(item -> item.getClass().equals(type))
                .map(type::cast)
                .findFirst();
    }

    public String errorMessage() {
        return errors.stream()
                .map(Error::getMessage)
                .collect(Collectors.joining(", "));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void throwIfHasErrors(String message) {
        if (hasErrors()) {
            throw new DomainException(message, this);
        }
    }
}
