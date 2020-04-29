package org.taonaw.managementsite.application.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ErrorInfo {
    private int code;
    private String message;

    public boolean is(@NonNull ErrorCode errorCode) {
        return code == errorCode.getCode();
    }
}
