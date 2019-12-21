package org.taonaw.common.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionCodes {
    ReservationDuplication(10000, "予約が重複しています。"),
    PersonalReservationNotStartedAccepting(10001, "個人練習の予約はまだ開始していません。");

    private final int code;
    private final String defaultMessage;
}
