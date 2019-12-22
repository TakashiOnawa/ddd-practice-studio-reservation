package org.taonaw.common.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionCodes {
    ReservationDuplication(10000, "予約が重複しています。"),
    ReservationNotStartedReception(10001, "予約はまだ受付を開始していません。"),
    ReservationOverMaxNumberOfUsers(10002, "利用最大人数を超えています。");

    private final int code;
    private final String defaultMessage;
}
