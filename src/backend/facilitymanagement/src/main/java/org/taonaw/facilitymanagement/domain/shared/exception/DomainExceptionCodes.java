package org.taonaw.facilitymanagement.domain.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionCodes {
    LoginAccountNotFound(20000, "ログインアカウントが存在しません。"),
    LoginAccountPasswordNotMatched(20001, "ログインパスワードが一致しません。"),
    AccountDuplicated(20002, "アカウントが重複しています。");

    private final int code;
    private final String defaultMessage;
}
