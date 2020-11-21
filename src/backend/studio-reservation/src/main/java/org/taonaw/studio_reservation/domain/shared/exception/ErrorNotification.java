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

    public void addError(@NonNull Error error) {
        errors.add(error);
    }

    public void addError(@NonNull Optional<Error> error) {
        error.ifPresent(errors::add);
    }

    public void addError(@NonNull ErrorNotification errorNotification) {
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

    public boolean noErrors() {
        return errors.isEmpty();
    }

    public boolean hasErrors() {
        return !noErrors();
    }

    public void throwIfHasErrors(String message) {
        if (hasErrors()) {
            throw new DomainException(message, this);
        }
    }
}
