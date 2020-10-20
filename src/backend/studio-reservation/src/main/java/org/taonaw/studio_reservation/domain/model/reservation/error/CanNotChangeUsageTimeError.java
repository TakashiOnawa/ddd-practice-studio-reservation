package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class CanNotChangeUsageTimeError extends Error {
    public CanNotChangeUsageTimeError() {
        super("キャンセル料金がかかるため利用時間を変更できません。");
    }
}
