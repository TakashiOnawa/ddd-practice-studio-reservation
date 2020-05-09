package org.taonaw.reservation.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ReservationDuplicated(30000, "予約が重複しています。"),
    EquipmentOutOfStocks(30001, "予約機材の在庫が余っていません。"),
    OverMaxNumberOfUsers(30001, "最大利用可能人数を超えています。"),
    OutOfOpeningHours(30002, "営業時間外の予約はできません。"),
    ReservationNotStarted(30003, "予約受付が開始されていません。"),
    StartTimeTypeNotSatisfied(30004, "スタート時間を満たしていません。"),
    CanNotChangeUseTime(30005, "キャンセル料がかかるため利用時間の変更はできません。"),
    CanNotChangeUserInformation(30006, "会員の利用者情報は変更できません。"),
    CanNotCancelByDifferentMember(30007, "異なる会員によるキャンセルはできません。"),
    CanNotCancelThereIsCancellationFee(30008, "キャンセル料がかかるためキャンセルできません。"),
    ;

    private final int code;
    private final String defaultMessage;
}
