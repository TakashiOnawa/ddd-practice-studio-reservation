package org.taonaw.facilitymanagement.domain.exception;

import lombok.NonNull;

public class ChangeCancellationFeeRatesException extends DomainException {
    public ChangeCancellationFeeRatesException(@NonNull String message) {
        super(message);
    }
}
