package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class CanNotChangePracticeTypeError extends Error {
    public CanNotChangePracticeTypeError() {
        super("キャンセル料金がかかるため練習区分を変更できません。");
    }
}
