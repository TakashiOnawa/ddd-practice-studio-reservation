package org.taonaw.reservation.domain.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainExceptionCodes {
    ReservationDuplicated(10000, "予約が重複しています。"),
    ReservationNotStarted(10001, "予約を開始していません。"),
    OverMaxNumberOfUsers(10002, "利用最大人数を超えています。"),
    EquipmentOutOfStocks(10003, "機材の在庫が余っていません。"),
    StartTimeTypeNotSatisfied(10004, "スタート時間を満たしていません。"),
    OutOfOpeningHours(1005, "営業時間外です。"),
    CannotChangeUseTimeBecauseThereIsCancellationFee(1006, "キャンセル料がかかるため利用時間の変更はできません。"),
    CannotCancelBecauseThereIsCancellationFee(1007, "キャンセル料がかかるためキャンセルできません。"),

    InvalidOperation(9999, "不正な操作です。");

    private final int code;
    private final String defaultMessage;
}
