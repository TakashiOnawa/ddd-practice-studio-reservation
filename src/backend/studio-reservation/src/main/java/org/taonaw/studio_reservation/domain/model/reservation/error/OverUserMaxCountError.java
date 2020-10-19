package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class OverUserMaxCountError extends Error {
    public OverUserMaxCountError() {
        super("最大利用者人数を超えています。");
    }
}
