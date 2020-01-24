package org.taonaw.identityaccess.domain.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionCodes {
    Test(20000, "テスト例外コード。");

    private final int code;
    private final String defaultMessage;
}
