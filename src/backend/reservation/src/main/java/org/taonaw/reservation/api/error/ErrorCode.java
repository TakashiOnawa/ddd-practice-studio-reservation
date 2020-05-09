package org.taonaw.reservation.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ReservationDuplicated(30001, "予約が重複しています。"),
    EquipmentOutOfStocks(30002, "予約機材の在庫が余っていません。"),
    OverMaxNumberOfUsers(30003, "最大利用可能人数を超えています。"),
    OutOfOpeningHours(30004, "営業時間外の予約はできません。"),
    ReservationNotStarted(30005, "予約受付が開始されていません。"),
    StartTimeTypeNotSatisfied(30006, "スタート時間を満たしていません。"),
    CanNotChangeUseTime(30007, "キャンセル料がかかるため利用時間の変更はできません。"),
    CanNotChangeUserInformation(30008, "会員の利用者情報は変更できません。"),
    CanNotCancelByDifferentMember(30009, "異なる会員によるキャンセルはできません。"),
    CanNotCancelThereIsCancellationFee(30010, "キャンセル料がかかるためキャンセルできません。"),
    ;

    private final int code;
    private final String defaultMessage;
}
