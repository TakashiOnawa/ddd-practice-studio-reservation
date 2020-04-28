package org.taonaw.identityaccess.api.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class
ErrorInformation {
    private final int code;
    private final String message;
}
