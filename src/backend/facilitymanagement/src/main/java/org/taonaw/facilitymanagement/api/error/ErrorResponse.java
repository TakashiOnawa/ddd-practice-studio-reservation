package org.taonaw.facilitymanagement.api.error;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse {
    private final List<ErrorInformation> errors = new ArrayList<>();

    public ErrorResponse(@NonNull ErrorCode errorCode) {
        errors.add(new ErrorInformation(errorCode));
    }

    public ErrorResponse(@NonNull ErrorInformation error) {
        errors.add(error);
    }

    public ErrorResponse(@NonNull List<ErrorInformation> errors) {
        this.errors.addAll(errors);
    }

    public List<ErrorInformation> getErrors() {
        return errors.stream().collect(Collectors.toUnmodifiableList());
    }
}
