package org.taonaw.managementsite.application.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AccountDuplicated(10000),
    LoginAccountNotFound(100001),
    LoginAccountUnAuthenticated(100002),
    MemberDuplicated(100003),
    LoginMemberNotFound(100004),
    LoginMemberUnAuthenticated(100005),

    ReservationDuplicated(30000),
    EquipmentOutOfStocks(30001),
    ;

    private final int code;

    private ErrorCode(int code) {
        this.code = code;
    }
}
