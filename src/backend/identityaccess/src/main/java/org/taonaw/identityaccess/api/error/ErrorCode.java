package org.taonaw.identityaccess.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    AccountDuplicated(10000),
    LoginAccountNotFound(100001),
    LoginAccountUnAuthenticated(100002),
    MemberDuplicated(100003),
    LoginMemberNotFound(100004),
    LoginMemberUnAuthenticated(100005),
    ;

    private final int code;
}
