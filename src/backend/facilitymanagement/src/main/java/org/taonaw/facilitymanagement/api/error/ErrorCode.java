package org.taonaw.facilitymanagement.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    CanNotChangeCancellationFeeRates(20001, "キャンセル料金の設定が不正です。"),
    ;

    private final int code;
    private final String defaultMessage;
}
