package org.taonaw.managementsite.application.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ErrorInformation {
    protected int code;
    protected String message;

    boolean is(@NonNull ErrorCode errorCode) {
        return code == errorCode.getCode();
    }
}
