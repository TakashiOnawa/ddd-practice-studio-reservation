package org.taonaw.managementsite.application.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AccountDuplicated(10001),
    LoginAccountNotFound(100002),
    LoginAccountUnAuthenticated(100003),
    MemberDuplicated(100004),
    LoginMemberNotFound(100005),
    LoginMemberUnAuthenticated(100006),

    ReservationDuplicated(30001),
    EquipmentOutOfStocks(30002),
    OverMaxNumberOfUsers(30003),
    OutOfOpeningHours(30004),
    ReservationNotStarted(30005),
    StartTimeTypeNotSatisfied(30006),
    CanNotChangeUseTime(30007),
    CanNotChangeUserInformation(30008),
    CanNotCancelByDifferentMember(30009),
    CanNotCancelThereIsCancellationFee(30010),
    ;

    private final int code;

    private ErrorCode(int code) {
        this.code = code;
    }
}
