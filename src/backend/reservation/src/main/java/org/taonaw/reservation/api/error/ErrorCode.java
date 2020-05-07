package org.taonaw.reservation.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ReservationDuplicated(30000),
    EquipmentOutOfStocks(30001)
    ;

    private final int code;
}
