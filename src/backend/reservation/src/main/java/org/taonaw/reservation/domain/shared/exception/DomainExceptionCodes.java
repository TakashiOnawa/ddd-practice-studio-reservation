package org.taonaw.reservation.domain.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionCodes {
    ReservationDuplicated(10000, "予約が重複しています。"),
    ReservationNotStarted(10001, "予約を開始していません。"),
    OverMaxNumberOfUsers(10002, "利用最大人数を超えています。"),
    EquipmentOutOfStock(10003, "機材の在庫が余っていません。"),
    StartTimeTypeNotSatisfied(10004, "スタート時間を満たしていません。");

    private final int code;
    private final String defaultMessage;
}
