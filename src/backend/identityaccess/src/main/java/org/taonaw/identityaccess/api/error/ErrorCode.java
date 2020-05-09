package org.taonaw.identityaccess.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    AccountDuplicated(10001, "アカウントが重複しています。"),
    LoginAccountNotFound(100002, "ログインアカウントが見つかりません。"),
    LoginAccountUnAuthenticated(100003, "ログインパスワードが一致しません。"),
    MemberDuplicated(100004, "会員が重複しています。"),
    LoginMemberNotFound(100005, "会員が存在しません。"),
    LoginMemberUnAuthenticated(100006, "会員のログインパスワードが一致しません。"),
    ;

    private final int code;
    private final String defaultMessage;
}
