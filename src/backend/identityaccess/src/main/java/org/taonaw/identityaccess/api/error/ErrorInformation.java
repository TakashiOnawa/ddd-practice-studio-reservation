package org.taonaw.identityaccess.api.error;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class ErrorInformation {
    private final int code;
    private final String message;

    public ErrorInformation(@NonNull ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getDefaultMessage();
    }

    public ErrorInformation(@NonNull ErrorCode errorCode, String message) {
        this.code = errorCode.getCode();
        this.message = message;
    }
}
