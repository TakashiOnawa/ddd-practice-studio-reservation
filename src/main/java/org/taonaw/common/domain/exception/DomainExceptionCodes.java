package org.taonaw.common.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionCodes {
    ReservationAlreadyReservedDuplication(10000, "既に予約されています。"),
    BeforeReservation(10001, "予約を開始していません。"),
    OverMaxNumberOfUsers(10002, "利用最大人数を超えています。"),
    EquipmentOutOfStock(10003, "機材の在庫が余っていません。"),
    aaa(10004, "aaa");

    private final int code;
    private final String defaultMessage;
}
