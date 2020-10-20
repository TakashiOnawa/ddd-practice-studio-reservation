package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class CanNotChangeUsageStudioError extends Error {
    public CanNotChangeUsageStudioError() {
        super("キャンセル料金がかかるため利用スタジオを変更できません。");
    }
}
